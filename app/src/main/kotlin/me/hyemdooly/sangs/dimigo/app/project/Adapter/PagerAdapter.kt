package me.hyemdooly.sangs.dimigo.app.project.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import me.hyemdooly.sangs.dimigo.app.project.Fragment.AchieveFragment
import me.hyemdooly.sangs.dimigo.app.project.Fragment.StatsFragment

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
            0 -> curFragment = AchieveFragment()
            1 -> curFragment = StatsFragment()
        }
        return curFragment
    }

    override fun getCount(): Int {
        return MAX_PAGE
    }
}