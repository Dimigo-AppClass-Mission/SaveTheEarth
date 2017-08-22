package me.hyemdooly.sangs.dimigo.app.project.Activity

import android.net.Uri
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import me.hyemdooly.sangs.dimigo.app.project.Fragment.AchieveFragment
import me.hyemdooly.sangs.dimigo.app.project.Fragment.StatsFragment
import me.hyemdooly.sangs.dimigo.app.project.R

class DetailActivity : AppCompatActivity(), AchieveFragment.OnFragmentInteractionListener, StatsFragment.OnFragmentInteractionListener {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)



        var viewPager: ViewPager = findViewById(R.id.view_pager)
        var adapter: me.hyemdooly.sangs.dimigo.app.project.Adapter.PagerAdapter
                = me.hyemdooly.sangs.dimigo.app.project.Adapter.PagerAdapter(supportFragmentManager)

        viewPager.adapter = adapter



    }

    fun onClick(v: View){
        when(v.id){
        }




    }

    override fun onFragmentInteraction(uri: Uri) {

    }

}

