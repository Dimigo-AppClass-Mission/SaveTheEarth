package me.hyemdooly.sangs.dimigo.app.project.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import me.hyemdooly.sangs.dimigo.app.project.fragment.DetailFragment
import me.hyemdooly.sangs.dimigo.app.project.fragment.MainFragment

/**
 * Created by dsa28s on 8/26/17.
 */

class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val pageListObj = arrayOf(MainFragment(), DetailFragment())

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
