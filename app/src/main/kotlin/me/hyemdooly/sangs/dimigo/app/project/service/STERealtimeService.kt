package me.hyemdooly.sangs.dimigo.app.project.service

import android.app.Notification
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import io.realm.Realm
import me.hyemdooly.sangs.dimigo.app.project.receiver.ScreenOnOffReceiver

import me.hyemdooly.sangs.dimigo.app.project.R



class STERealtimeService : Service() {

    lateinit var screenReceiver: ScreenOnOffReceiver

    override fun onCreate() {
        Realm.init(applicationContext)
        registerStatusReceiver()

        startForeground(33233317, getNotification(this))
    }

    override fun onDestroy() {
        unregisterStatusReceiver()
        stopForeground(true)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    private fun registerStatusReceiver() {
        screenReceiver = ScreenOnOffReceiver()
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_SCREEN_ON)
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        registerReceiver(screenReceiver, filter)

    }

    private fun unregisterStatusReceiver() {
        if(screenReceiver != null){
            unregisterReceiver(screenReceiver)
        }
    }

    private fun getNotification(paramContext: Context): Notification {
        val smallIcon = R.drawable.ic_eco_energy
        val notification = NotificationCompat.Builder(paramContext, "SaveEnergy")
                .setSmallIcon(smallIcon)
                .setPriority(NotificationCompat.PRIORITY_MIN)
                .setAutoCancel(true)
                .setWhen(0)
                .setContentTitle("휴대폰 사용절약 프로젝트 진행!")
                .setContentText("휴대폰을 사용하지 않으면 지구가 치유됩니다 :)").build()
        notification.flags = 16
        return notification
    }
}