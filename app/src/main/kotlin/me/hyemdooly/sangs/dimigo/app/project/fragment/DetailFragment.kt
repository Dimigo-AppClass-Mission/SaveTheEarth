package me.hyemdooly.sangs.dimigo.app.project.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import me.hyemdooly.sangs.dimigo.app.project.R

/**
 * Created by dsa28s on 8/26/17.
 */

class DetailFragment: Fragment() {
    private var rootView: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(R.layout.activity_detail, null, false)

        return rootView
    }
}