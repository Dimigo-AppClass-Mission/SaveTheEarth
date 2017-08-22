package me.hyemdooly.sangs.dimigo.app.project.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import me.hyemdooly.sangs.dimigo.app.project.fragment.AchieveFragment
import me.hyemdooly.sangs.dimigo.app.project.fragment.StatsFragment

/**
 * Created by songhyemin on 2017. 8. 22..
 */
class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private var MAX_PAGE: Int = 2
    private var curFragment: Fragment = Fragment()

    override fun getItem(position: Int): Fragment? {
        if(position < 0 || MAX_PAGE <= position){
           return null
        }
        when(position){
            0 -> curFragment = StatsFragment()
            1 -> curFragment = AchieveFragment()
        }
        return curFragment
    }

    override fun getCount(): Int {
        return MAX_PAGE
    }
}