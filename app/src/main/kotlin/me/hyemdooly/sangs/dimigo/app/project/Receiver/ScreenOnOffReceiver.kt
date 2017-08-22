package me.hyemdooly.sangs.dimigo.app.project.Receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * Created by songhyemin on 2017. 8. 22..
 */
class ScreenOnOffReceiver : BroadcastReceiver() {
    var firstTime: Long = 0
    var lastTime: Long = 0
    var countTime: Long = 0

    override fun onReceive(p0: Context?, p1: Intent?) {
        when(p1?.action){
            Intent.ACTION_SCREEN_OFF -> {
                lastTime = System.currentTimeMillis()
                Log.d("Screen", "OFF")
                if(firstTime > 0){
                    countTime = lastTime-firstTime
                }

                Log.d("Count", countTime.toString())

            }
            Intent.ACTION_SCREEN_ON -> {
                Log.d("Screen", "ON")
                firstTime = System.currentTimeMillis()
            }
        }
    }
}