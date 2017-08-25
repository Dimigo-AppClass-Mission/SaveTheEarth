package me.hyemdooly.sangs.dimigo.app.project.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View

import me.hyemdooly.sangs.dimigo.app.project.R

import me.hyemdooly.sangs.dimigo.app.project.view.TextThumbProgressBar.ProgressTextVisibility.Invisible
import me.hyemdooly.sangs.dimigo.app.project.view.TextThumbProgressBar.ProgressTextVisibility.Visible

class TextThumbProgressBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    interface OnProgressChangedListener {
        fun onProgressChange(current: Int, max: Int)
    }

    var max = 100
        set(maxProgress) {
            if (maxProgress > 0) {
                field = maxProgress
                invalidate()
            }
        }
    var progress = 0
        set(progress) {
            if (progress in 0..max) {
                field = progress
                invalidate()
            }
        }
    private var mReachedBarColor: Int = 0
    private var mUnreachedBarColor: Int = 0
    var textColor: Int = 0
        private set
    private var mTextSize: Float = 0.toFloat()
    var reachedBarHeight: Float = 0.toFloat()
    var unreachedBarHeight: Float = 0.toFloat()
    var suffix: String? = "%"
        set(suffix) = if (suffix == null) {
            field = ""
        } else {
            field = suffix
        }
    var prefix: String? = ""
        set(prefix) = if (prefix == null)
            field = ""
        else {
            field = prefix
        }

    private val default_text_color = Color.rgb(66, 145, 241)
    private val default_reached_color = Color.rgb(66, 145, 241)
    private val default_unreached_color = Color.rgb(204, 204, 204)
    private val default_progress_text_offset: Float
    private val default_text_size: Float
    private val default_reached_bar_height: Float
    private val default_unreached_bar_height: Float

    private var mDrawTextWidth: Float = 0.toFloat()
    private var mDrawTextStart: Float = 0.toFloat()
    private var mDrawTextEnd: Float = 0.toFloat()
    private var mCurrentDrawText: String? = null
    private var mReachedBarPaint: Paint? = null
    private var mUnreachedBarPaint: Paint? = null
    private var mTextPaint: Paint? = null
    private var mTextBorderPaint: Paint? = null
    private val mUnreachedRectF = RectF(0f, 0f, 0f, 0f)
    private val mReachedRectF = RectF(0f, 0f, 0f, 0f)
    private val mOffset: Float
    private var mDrawUnreachedBar = true

    private var mDrawReachedBar = true

    var progressTextVisibility = true
        private set

    private var mListener: OnProgressChangedListener? = null

    enum class ProgressTextVisibility {
        Visible, Invisible
    }

    init {
        default_reached_bar_height = dp2px(1.5f)
        default_unreached_bar_height = dp2px(1.0f)
        default_text_size = sp2px(10f)
        default_progress_text_offset = dp2px(3.0f)

        val attributes = context.theme.obtainStyledAttributes(attrs, R.styleable.TextThumbProgressBar,
                defStyleAttr, 0)

        mReachedBarColor = attributes.getColor(R.styleable.TextThumbProgressBar_progress_reached_color, default_reached_color)
        mUnreachedBarColor = attributes.getColor(R.styleable.TextThumbProgressBar_progress_unreached_color, default_unreached_color)
        textColor = attributes.getColor(R.styleable.TextThumbProgressBar_progress_text_color, default_text_color)
        mTextSize = attributes.getDimension(R.styleable.TextThumbProgressBar_progress_text_size, default_text_size)

        reachedBarHeight = attributes.getDimension(R.styleable.TextThumbProgressBar_progress_reached_bar_height, default_reached_bar_height)
        unreachedBarHeight = attributes.getDimension(R.styleable.TextThumbProgressBar_progress_unreached_bar_height, default_unreached_bar_height)
        mOffset = attributes.getDimension(R.styleable.TextThumbProgressBar_progress_text_offset, default_progress_text_offset)

        val textVisible = attributes.getInt(R.styleable.TextThumbProgressBar_progress_text_visibility, PROGRESS_TEXT_VISIBLE)
        if (textVisible != PROGRESS_TEXT_VISIBLE) {
            progressTextVisibility = false
        }

        progress = attributes.getInt(R.styleable.TextThumbProgressBar_progress_current, 0)
        max = attributes.getInt(R.styleable.TextThumbProgressBar_progress_max, 100)

        attributes.recycle()
        initializePainters()
    }

    override fun getSuggestedMinimumWidth(): Int {
        return mTextSize.toInt()
    }

    override fun getSuggestedMinimumHeight(): Int {
        return Math.max(mTextSize.toInt(), Math.max(reachedBarHeight.toInt(), unreachedBarHeight.toInt()))
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(measure(widthMeasureSpec, true), measure(heightMeasureSpec, false))
    }

    private fun measure(measureSpec: Int, isWidth: Boolean): Int {
        var result: Int
        val mode = View.MeasureSpec.getMode(measureSpec)
        val size = View.MeasureSpec.getSize(measureSpec)
        val padding = if (isWidth) paddingLeft + paddingRight else paddingTop + paddingBottom
        if (mode == View.MeasureSpec.EXACTLY) {
            result = size
        } else {
            result = if (isWidth) suggestedMinimumWidth else suggestedMinimumHeight
            result += padding
            if (mode == View.MeasureSpec.AT_MOST) {
                if (isWidth) {
                    result = Math.max(result, size)
                } else {
                    result = Math.min(result, size)
                }
            }
        }
        return result
    }

    override fun onDraw(canvas: Canvas) {
        if (progressTextVisibility) {
            calculateDrawRectF()
        } else {
            calculateDrawRectFWithoutProgressText()
        }

        if (mDrawReachedBar) {
            canvas.drawRect(mReachedRectF, mReachedBarPaint!!)
        }

        if (mDrawUnreachedBar) {
            canvas.drawRect(mUnreachedRectF, mUnreachedBarPaint!!)
        }

        if (progressTextVisibility)
            canvas.drawRect(mDrawTextStart - 16, mDrawTextEnd - mTextPaint!!.textSize - 15, mDrawTextStart + mTextPaint!!.measureText(mCurrentDrawText) + 16, mDrawTextEnd + mTextPaint!!.textSize, mTextBorderPaint!!)
            canvas.drawText(mCurrentDrawText!!, mDrawTextStart, mDrawTextEnd, mTextPaint!!)
    }

    private fun initializePainters() {
        mReachedBarPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mReachedBarPaint!!.color = mReachedBarColor

        mUnreachedBarPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mUnreachedBarPaint!!.color = mUnreachedBarColor

        mTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mTextPaint!!.color = textColor
        mTextPaint!!.textSize = mTextSize

        mTextBorderPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mTextBorderPaint!!.color = Color.WHITE
    }


    private fun calculateDrawRectFWithoutProgressText() {
        mReachedRectF.left = paddingLeft.toFloat()
        mReachedRectF.top = height / 2.0f - reachedBarHeight / 2.0f
        mReachedRectF.right = (width - paddingLeft - paddingRight) / (max * 1.0f) * progress + paddingLeft
        mReachedRectF.bottom = height / 2.0f + reachedBarHeight / 2.0f

        mUnreachedRectF.left = mReachedRectF.right
        mUnreachedRectF.right = (width - paddingRight).toFloat()
        mUnreachedRectF.top = height / 2.0f + -unreachedBarHeight / 2.0f
        mUnreachedRectF.bottom = height / 2.0f + unreachedBarHeight / 2.0f
    }

    public fun setProgressBarText(value: String) {
        mCurrentDrawText = value
        invalidate()
    }

    private fun calculateDrawRectF() {

        //mCurrentDrawText = String.format("%d", progress * 100 / max)
        //mCurrentDrawText = prefix + mCurrentDrawText + suffix
        mDrawTextWidth = mTextPaint!!.measureText(mCurrentDrawText)

        if (progress == 0) {
            mDrawReachedBar = false
            mDrawTextStart = paddingLeft.toFloat()
        } else {
            mDrawReachedBar = true
            mReachedRectF.left = paddingLeft.toFloat()
            mReachedRectF.top = height / 2.0f - reachedBarHeight / 2.0f
            mReachedRectF.right = (width - paddingLeft - paddingRight) / (max * 1.0f) * progress - mOffset + paddingLeft
            mReachedRectF.bottom = height / 2.0f + reachedBarHeight / 2.0f
            mDrawTextStart = mReachedRectF.right + mOffset
        }

        mDrawTextEnd = (height / 2.0f - (mTextPaint!!.descent() + mTextPaint!!.ascent()) / 2.0f).toInt().toFloat()

        if (mDrawTextStart + mDrawTextWidth >= width - paddingRight) {
            mDrawTextStart = width.toFloat() - paddingRight.toFloat() - mDrawTextWidth
            mReachedRectF.right = mDrawTextStart - mOffset
        }

        val unreachedBarStart = mDrawTextStart + mDrawTextWidth + mOffset
        if (unreachedBarStart >= width - paddingRight) {
            mDrawUnreachedBar = false
        } else {
            mDrawUnreachedBar = true
            mUnreachedRectF.left = unreachedBarStart
            mUnreachedRectF.right = (width - paddingRight).toFloat()
            mUnreachedRectF.top = height / 2.0f + -unreachedBarHeight / 2.0f
            mUnreachedRectF.bottom = height / 2.0f + unreachedBarHeight / 2.0f
        }
    }

    var progressTextSize: Float
        get() = mTextSize
        set(textSize) {
            this.mTextSize = textSize
            mTextPaint!!.textSize = mTextSize
            invalidate()
        }

    var unreachedBarColor: Int
        get() = mUnreachedBarColor
        set(barColor) {
            this.mUnreachedBarColor = barColor
            mUnreachedBarPaint!!.color = mUnreachedBarColor
            invalidate()
        }

    var reachedBarColor: Int
        get() = mReachedBarColor
        set(progressColor) {
            this.mReachedBarColor = progressColor
            mReachedBarPaint!!.color = mReachedBarColor
            invalidate()
        }

    fun setProgressTextColor(textColor: Int) {
        this.textColor = textColor
        mTextPaint!!.color = this.textColor
        invalidate()
    }

    fun incrementProgressBy(by: Int) {
        if (by > 0) {
            progress = progress + by
        }

        if (mListener != null) {
            mListener!!.onProgressChange(progress, max)
        }
    }

    override fun onSaveInstanceState(): Parcelable? {
        val bundle = Bundle()
        bundle.putParcelable(INSTANCE_STATE, super.onSaveInstanceState())
        bundle.putInt(INSTANCE_TEXT_COLOR, textColor)
        bundle.putFloat(INSTANCE_TEXT_SIZE, progressTextSize)
        bundle.putFloat(INSTANCE_REACHED_BAR_HEIGHT, reachedBarHeight)
        bundle.putFloat(INSTANCE_UNREACHED_BAR_HEIGHT, unreachedBarHeight)
        bundle.putInt(INSTANCE_REACHED_BAR_COLOR, reachedBarColor)
        bundle.putInt(INSTANCE_UNREACHED_BAR_COLOR, unreachedBarColor)
        bundle.putInt(INSTANCE_MAX, max)
        bundle.putInt(INSTANCE_PROGRESS, progress)
        bundle.putString(INSTANCE_SUFFIX, suffix)
        bundle.putString(INSTANCE_PREFIX, prefix)
        bundle.putBoolean(INSTANCE_TEXT_VISIBILITY, progressTextVisibility)
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        if (state is Bundle) {
            textColor = state.getInt(INSTANCE_TEXT_COLOR)
            mTextSize = state.getFloat(INSTANCE_TEXT_SIZE)
            reachedBarHeight = state.getFloat(INSTANCE_REACHED_BAR_HEIGHT)
            unreachedBarHeight = state.getFloat(INSTANCE_UNREACHED_BAR_HEIGHT)
            mReachedBarColor = state.getInt(INSTANCE_REACHED_BAR_COLOR)
            mUnreachedBarColor = state.getInt(INSTANCE_UNREACHED_BAR_COLOR)
            initializePainters()
            max = state.getInt(INSTANCE_MAX)
            progress = state.getInt(INSTANCE_PROGRESS)
            prefix = state.getString(INSTANCE_PREFIX)
            suffix = state.getString(INSTANCE_SUFFIX)
            setProgressTextVisibility(if (state.getBoolean(INSTANCE_TEXT_VISIBILITY)) Visible else Invisible)
            super.onRestoreInstanceState(state.getParcelable(INSTANCE_STATE))
            return
        }
        super.onRestoreInstanceState(state)
    }

    fun dp2px(dp: Float): Float {
        val scale = resources.displayMetrics.density
        return dp * scale + 0.5f
    }

    fun sp2px(sp: Float): Float {
        val scale = resources.displayMetrics.scaledDensity
        return sp * scale
    }

    fun setProgressTextVisibility(visibility: ProgressTextVisibility) {
        progressTextVisibility = visibility == Visible
        invalidate()
    }

    fun setOnProgressBarListener(listener: OnProgressChangedListener) {
        mListener = listener
    }

    companion object {
        private val INSTANCE_STATE = "saved_instance"
        private val INSTANCE_TEXT_COLOR = "text_color"
        private val INSTANCE_TEXT_SIZE = "text_size"
        private val INSTANCE_REACHED_BAR_HEIGHT = "reached_bar_height"
        private val INSTANCE_REACHED_BAR_COLOR = "reached_bar_color"
        private val INSTANCE_UNREACHED_BAR_HEIGHT = "unreached_bar_height"
        private val INSTANCE_UNREACHED_BAR_COLOR = "unreached_bar_color"
        private val INSTANCE_MAX = "max"
        private val INSTANCE_PROGRESS = "progress"
        private val INSTANCE_SUFFIX = "suffix"
        private val INSTANCE_PREFIX = "prefix"
        private val INSTANCE_TEXT_VISIBILITY = "text_visibility"

        private val PROGRESS_TEXT_VISIBLE = 0
    }
}