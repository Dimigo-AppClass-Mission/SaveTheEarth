package me.hyemdooly.sangs.dimigo.app.project

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setStatusBar(this@SplashActivity, getColor(R.color.colorUniverse))
        }

        Handler().postDelayed(
                {
                    var intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()

        }, 3000)

    }

    fun setStatusBar(activity: Activity, color: Int) {
        var window: Window = activity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = color
        }
    }


}


