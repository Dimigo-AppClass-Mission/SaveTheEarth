package me.hyemdooly.sangs.dimigo.app.project.`interface`

/**
 * Created by dsa28s on 8/27/17.
 */

interface OnPagerPageScrollListener {
    fun onCommunicationWithActivityPagerScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int)
    fun onCommunicationWithActivityPagerScrollSelected(position: Int)
}