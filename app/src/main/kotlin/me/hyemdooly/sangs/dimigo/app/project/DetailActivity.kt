package me.hyemdooly.sangs.dimigo.app.project

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import android.widget.ToggleButton
import me.hyemdooly.sangs.dimigo.app.project.fragment.AchieveFragment
import me.hyemdooly.sangs.dimigo.app.project.fragment.StatsFragment
import me.hyemdooly.sangs.dimigo.app.project.service.ScreenOnOffService

class DetailActivity : AppCompatActivity() {

    private var backpressed: Long = 0
    lateinit var viewPager: ViewPager
    lateinit var statsButton: ToggleButton
    lateinit var achieveButton: ToggleButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_detail)

        /*setStatusBar(this@DetailActivity, Color.WHITE)

        var intent: Intent = Intent(this@DetailActivity, ScreenOnOffService::class.java)
        startService(intent)

        statsButton = findViewById(R.id.stats_button)
        achieveButton = findViewById(R.id.achieve_button)

        viewPager = findViewById(R.id.view_pager)
        var adapter: me.hyemdooly.sangs.dimigo.app.project.adapter.PagerAdapter
                = me.hyemdooly.sangs.dimigo.app.project.adapter.PagerAdapter(supportFragmentManager)

        viewPager.adapter = adapter

        viewPager.currentItem = 0
        statsButton.isChecked = true


        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                when(position){
                    0 -> {
                        statsButton.isChecked = true
                        achieveButton.isChecked = false
                    }
                    1 -> {
                        achieveButton.isChecked = true
                        statsButton.isChecked = false
                    }
                }
            }

        })*/


    }


    /*fun onClick(v: View){
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

    fun setStatusBar(activity: Activity, color: Int) {
        var window: Window = activity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = color
        }
    }

    override fun onBackPressed() {
        if(backpressed+2000 > System.currentTimeMillis()){
            super.onBackPressed()
        } else{
            Toast.makeText(this, "한번 더 누르면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
        backpressed = System.currentTimeMillis()

    }*/


}

