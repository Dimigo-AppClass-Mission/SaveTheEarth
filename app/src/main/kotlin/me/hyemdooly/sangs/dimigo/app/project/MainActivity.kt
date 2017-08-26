package me.hyemdooly.sangs.dimigo.app.project

import android.animation.ArgbEvaluator
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast

import me.hyemdooly.sangs.dimigo.app.project.adapter.MainPagerAdapter
import me.hyemdooly.sangs.dimigo.app.project.fragment.DetailFragment
import me.hyemdooly.sangs.dimigo.app.project.fragment.MainFragment
import me.hyemdooly.sangs.dimigo.app.project.util.getStatusBarHeight
import me.hyemdooly.sangs.dimigo.app.project.view.VerticalViewPager

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import android.animation.ObjectAnimator
import me.hyemdooly.sangs.dimigo.app.project.`interface`.OnPagerPageScrollListener
import me.hyemdooly.sangs.dimigo.app.project.util.convertIntegerToDp


class MainActivity : AppCompatActivity() {
    private var backPressed: Long = 0

    private val mainContainer by lazy { findViewById<RelativeLayout>(R.id.main_activity_root_view) }
    private val statusbarContainer by lazy { findViewById<RelativeLayout>(R.id.main_activity_statusbar_area_container) }
    private val pagerView by lazy { findViewById<VerticalViewPager>(R.id.main_activity_pager) }
    private val titleText by lazy { findViewById<TextView>(R.id.main_activity_title_text) }
    private val projectTitleText by lazy { findViewById<TextView>(R.id.main_activity_project_title_text) }

    private val pagerAdapter by lazy { MainPagerAdapter(supportFragmentManager) }
    private var evaluator = ArgbEvaluator()
    private val wbColors = intArrayOf(Color.WHITE, Color.BLACK)
    private val pwColors = intArrayOf(Color.parseColor("#270F30"), Color.WHITE)

    private var communicationPagerListener1: OnPagerPageScrollListener? = null
    private var communicationPagerListener2: OnPagerPageScrollListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pagerView.adapter = pagerAdapter

        pagerView.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                val wbColor = evaluator.evaluate(positionOffset, wbColors[position], wbColors[if (position == 1) position else position + 1]) as Int
                val pwColor = evaluator.evaluate(positionOffset, pwColors[position], pwColors[if (position == 1) position else position + 1]) as Int
                titleText.setTextColor(wbColor)
                projectTitleText.setTextColor(wbColor)
                mainContainer.setBackgroundColor(pwColor)

                if(communicationPagerListener1 != null) {
                    communicationPagerListener1!!.onCommunicationWithActivityPagerScrolled(position, positionOffset, positionOffsetPixels)
                }

                if(communicationPagerListener2 != null) {
                    communicationPagerListener2!!.onCommunicationWithActivityPagerScrolled(position, positionOffset, positionOffsetPixels)
                }
            }

            override fun onPageSelected(position: Int) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    when(position) {
                        0 -> {
                            val layoutParams = RelativeLayout.LayoutParams(0, 0)
                            statusbarContainer.layoutParams = layoutParams
                        }
                        else -> {
                            val layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, getStatusBarHeight(this@MainActivity))
                            statusbarContainer.layoutParams = layoutParams
                        }
                    }
                }

                if(communicationPagerListener1 != null) {
                    communicationPagerListener1!!.onCommunicationWithActivityPagerScrollSelected(position)
                }

                if(communicationPagerListener2 != null) {
                    communicationPagerListener2!!.onCommunicationWithActivityPagerScrollSelected(position)
                }
            }
        })
    }

    fun setOnCommunicationPagerListenerInMain(listener: OnPagerPageScrollListener) {
        communicationPagerListener1 = listener
    }

    fun setOnCommunicationPagerListenerInDetail(listener: OnPagerPageScrollListener) {
        communicationPagerListener2 = listener
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase!!))
    }

    override fun onBackPressed() {
        if (backPressed + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            Toast.makeText(this, "한번 더 누르면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
        backPressed = System.currentTimeMillis()

    }

}
