package me.hyemdooly.sangs.dimigo.app.project.service

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import io.realm.Realm
import me.hyemdooly.sangs.dimigo.app.project.receiver.ScreenOnOffReceiver

class ScreenOnOffService() : Service() {

    lateinit var screenReceiver: ScreenOnOffReceiver

    override fun onCreate() {
        Realm.init(applicationContext)
        registerStatusReceiver()
    }

    override fun onDestroy() {
        unregisterStatusReceiver()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null

    }

    private fun registerStatusReceiver() {
        screenReceiver = ScreenOnOffReceiver()
        var filter: IntentFilter = IntentFilter()
        filter.addAction(Intent.ACTION_SCREEN_ON)
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        registerReceiver(screenReceiver, filter)

    }

    private fun unregisterStatusReceiver() {
        if(screenReceiver != null){
            unregisterReceiver(screenReceiver)
        }
    }

}