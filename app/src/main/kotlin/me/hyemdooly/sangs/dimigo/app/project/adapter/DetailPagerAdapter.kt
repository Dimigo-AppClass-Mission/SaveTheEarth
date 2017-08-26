package me.hyemdooly.sangs.dimigo.app.project.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import me.hyemdooly.sangs.dimigo.app.project.fragment.AchieveFragment
import me.hyemdooly.sangs.dimigo.app.project.fragment.StatsFragment

class DetailPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val pageListObj = arrayOf(StatsFragment(), AchieveFragment())

    override fun getItem(position: Int): Fragment {
        return pageListObj[position]
    }

    override fun getCount(): Int {
        return 2
    }

    fun getPage(number: Int): Fragment {
        return pageListObj[number]
    }
}