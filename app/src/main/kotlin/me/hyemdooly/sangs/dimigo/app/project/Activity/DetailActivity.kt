package me.hyemdooly.sangs.dimigo.app.project.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ToggleButton
import me.hyemdooly.sangs.dimigo.app.project.Fragment.AchieveFragment
import me.hyemdooly.sangs.dimigo.app.project.Fragment.StatsFragment
import me.hyemdooly.sangs.dimigo.app.project.R
import me.hyemdooly.sangs.dimigo.app.project.Service.ScreenOnOffService

class DetailActivity : AppCompatActivity(), AchieveFragment.OnFragmentInteractionListener, StatsFragment.OnFragmentInteractionListener {

    lateinit var viewPager: ViewPager
    lateinit var statsButton: ToggleButton
    lateinit var achieveButton: ToggleButton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var intent: Intent = Intent(this@DetailActivity, ScreenOnOffService::class.java)
        startService(intent)

        statsButton = findViewById(R.id.stats_button)
        achieveButton = findViewById(R.id.achieve_button)

        viewPager = findViewById(R.id.view_pager)
        var adapter: me.hyemdooly.sangs.dimigo.app.project.Adapter.PagerAdapter
                = me.hyemdooly.sangs.dimigo.app.project.Adapter.PagerAdapter(supportFragmentManager)

        viewPager.adapter = adapter

        viewPager.currentItem = 0
        statsButton.isChecked = true;



    }

    fun onClick(v: View){
        when(v.id){
            R.id.stats_button -> {
                viewPager.currentItem = 0
                achieveButton.isChecked = false
                if(!statsButton.isChecked){
                    statsButton.isChecked = true
                }

            }
            R.id.achieve_button -> {
                viewPager.currentItem = 1
                statsButton.isChecked = false
                if(!achieveButton.isChecked){
                    achieveButton.isChecked = true
                }

            }
        }




    }

    override fun onFragmentInteraction(uri: Uri) {

    }

}

