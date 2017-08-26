package me.hyemdooly.sangs.dimigo.app.project.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout

import me.hyemdooly.sangs.dimigo.app.project.R
import me.hyemdooly.sangs.dimigo.app.project.adapter.DetailPagerAdapter

/**
 * Created by dsa28s on 8/26/17.
 */

class DetailFragment: Fragment() {
    private var rootView: View? = null

    var buttonContainer: LinearLayout? = null
    var statsButton: CardView? = null
    var archievementButton: CardView? = null
    var slideUpPanelContainer: CardView? = null
    var pager: ViewPager? = null

    var pagerAdapter: DetailPagerAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(R.layout.fragment_detail, null, false)

        buttonContainer = rootView!!.findViewById(R.id.detail_activity_tab_button_container)
        statsButton = rootView!!.findViewById(R.id.detail_fragment_stats_button)
        archievementButton = rootView!!.findViewById(R.id.detail_fragment_archievement_button)
        slideUpPanelContainer = rootView!!.findViewById(R.id.detail_fragment_bottom_slide_panel_container)
        pager = rootView!!.findViewById(R.id.detail_fragment_pager)

        pagerAdapter = DetailPagerAdapter(activity.supportFragmentManager)
        pager!!.adapter = pagerAdapter

        return rootView
    }
}