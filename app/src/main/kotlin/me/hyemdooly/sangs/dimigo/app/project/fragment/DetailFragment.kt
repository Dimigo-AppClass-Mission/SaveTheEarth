package me.hyemdooly.sangs.dimigo.app.project.fragment

import android.animation.ObjectAnimator
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import me.hyemdooly.sangs.dimigo.app.project.MainActivity

import me.hyemdooly.sangs.dimigo.app.project.R
import me.hyemdooly.sangs.dimigo.app.project.`interface`.OnPagerPageScrollListener
import me.hyemdooly.sangs.dimigo.app.project.adapter.DetailPagerAdapter
import me.hyemdooly.sangs.dimigo.app.project.util.convertIntegerToDp

/**
 * Created by dsa28s on 8/26/17.
 */

class DetailFragment: Fragment() {
    private var rootView: View? = null

    var buttonContainer: LinearLayout? = null
    var statsButton: CardView? = null
    var statsButtonIcon: AppCompatImageView? = null
    var statsButtonText: TextView? = null

    var achievementButton: CardView? = null
    var achievementButtonIcon: AppCompatImageView? = null
    var achievementButtonText: TextView? = null

    var slideUpPanelContainer: CardView? = null
    var pager: ViewPager? = null

    var pagerAdapter: DetailPagerAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(R.layout.fragment_detail, null, false)

        buttonContainer = rootView!!.findViewById(R.id.detail_activity_tab_button_container)
        statsButton = rootView!!.findViewById(R.id.detail_fragment_stats_button)
        statsButtonIcon = rootView!!.findViewById(R.id.detail_fragment_stats_button_icon)
        statsButtonText = rootView!!.findViewById(R.id.detail_fragment_stats_button_text)
        achievementButton = rootView!!.findViewById(R.id.detail_fragment_archievement_button)
        achievementButtonIcon = rootView!!.findViewById(R.id.detail_fragment_archievement_button_icon)
        achievementButtonText = rootView!!.findViewById(R.id.detail_fragment_archievement_button_text)
        slideUpPanelContainer = rootView!!.findViewById(R.id.detail_fragment_bottom_slide_panel_container)
        pager = rootView!!.findViewById(R.id.detail_fragment_pager)

        pagerAdapter = DetailPagerAdapter(activity.supportFragmentManager)
        pager!!.adapter = pagerAdapter

        pager!!.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                setButtonFocusStatus(position == 0)
            }
        })

        (activity as MainActivity).setOnCommunicationPagerListenerInDetail(object: OnPagerPageScrollListener {
            override fun onCommunicationWithActivityPagerScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                when(position) {
                    0 -> {
                        buttonContainer!!.alpha = positionOffset
                    }
                    else -> {
                        buttonContainer!!.alpha = 1 - positionOffset
                    }
                }
            }

            override fun onCommunicationWithActivityPagerScrollSelected(position: Int) {
                when (position) {
                    0 -> {
                        buttonContainer!!.alpha = 0.toFloat()
                        startElevationAnimation(appear = false)
                    }
                    1 -> {
                        buttonContainer!!.alpha = 1.toFloat()
                        startElevationAnimation(appear = true)
                    }
                }
            }
        })

        setButtonFocusStatus(true)

        statsButton!!.setOnClickListener {
            pager!!.setCurrentItem(0, true)
        }

        achievementButton!!.setOnClickListener {
            pager!!.setCurrentItem(1, true)
        }

        return rootView
    }

    private fun startElevationAnimation(appear: Boolean) {
        if(appear) {
            Handler().postDelayed({
                val animator = ObjectAnimator.ofFloat(statsButton!!, "cardElevation", 0.toFloat(), convertIntegerToDp(resources, 4.toFloat()))
                val animator2 = ObjectAnimator.ofFloat(achievementButton!!, "cardElevation", 0.toFloat(), convertIntegerToDp(resources, 4.toFloat()))
                val animator3 = ObjectAnimator.ofFloat(slideUpPanelContainer!!, "cardElevation", 0.toFloat(), convertIntegerToDp(resources, 2.toFloat()))

                animator.start()
                animator2.start()
                animator3.start()
            }, 500)
        } else {
            Handler().postDelayed({
                val animator = ObjectAnimator.ofFloat(statsButton!!, "cardElevation", convertIntegerToDp(resources, 4.toFloat()), 0.toFloat())
                val animator2 = ObjectAnimator.ofFloat(achievementButton!!, "cardElevation", convertIntegerToDp(resources, 4.toFloat()), 0.toFloat())
                val animator3 = ObjectAnimator.ofFloat(slideUpPanelContainer!!, "cardElevation", convertIntegerToDp(resources, 2.toFloat()), 0.toFloat())

                animator.start()
                animator2.start()
                animator3.start()
            }, 500)
        }
    }

    private fun setButtonFocusStatus(isStatButton: Boolean) {
        if(isStatButton) {
            statsButton!!.setCardBackgroundColor(Color.parseColor("#29C9BD"))
            statsButtonIcon!!.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)
            statsButtonText!!.setTextColor(Color.WHITE)

            achievementButton!!.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
            achievementButtonIcon!!.setColorFilter(Color.parseColor("#C0C9CF"), PorterDuff.Mode.SRC_IN)
            achievementButtonText!!.setTextColor(Color.parseColor("#C0C9CF"))
        } else {
            statsButton!!.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
            statsButtonIcon!!.setColorFilter(Color.parseColor("#C0C9CF"), PorterDuff.Mode.SRC_IN)
            statsButtonText!!.setTextColor(Color.parseColor("#C0C9CF"))

            achievementButton!!.setCardBackgroundColor(Color.parseColor("#29C9BD"))
            achievementButtonIcon!!.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)
            achievementButtonText!!.setTextColor(Color.WHITE)
        }
    }
}