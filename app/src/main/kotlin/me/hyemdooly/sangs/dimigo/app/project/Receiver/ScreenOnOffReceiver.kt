package me.hyemdooly.sangs.dimigo.app.project.Receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * Created by songhyemin on 2017. 8. 22..
 */
class ScreenOnOffReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        when(p1?.action){
            Intent.ACTION_SCREEN_OFF -> Log.d("Screen", "OFF")
            Intent.ACTION_SCREEN_ON -> Log.d("Screen", "ON")
        }
    }
}