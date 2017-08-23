package me.hyemdooly.sangs.dimigo.app.project.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

/**
 * Created by songhyemin on 2017. 8. 23..
 */
class DottedProgress(context: Context) : View(context) {

    private var dotRadius: Int = 5
    private var dotAmount: Int = 10


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var paint: Paint = Paint()
        paint.color = Color.parseColor("#9CD670")

    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var width: Int = 20*9
        var height: Int = dotRadius*2
        setMeasuredDimension(width, height)



    }

    private fun createDot(canvas: Canvas, paint: Paint){

        var i: Int = 0
        while (i < dotAmount){
            canvas.drawCircle((10+(i*20)).toFloat(), dotRadius.toFloat(), dotRadius.toFloat(), paint);
        }


    }



}