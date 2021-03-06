package me.hyemdooly.sangs.dimigo.app.project.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import me.hyemdooly.sangs.dimigo.app.project.database.DataController
import java.util.*

class ScreenOnOffReceiver : BroadcastReceiver() {
    var dataController: DataController? = null
    var firstTime: Long = System.currentTimeMillis()
    var lastTime: Long = 0
    var countTime: Long = 0

    override fun onReceive(p0: Context?, p1: Intent?) {
        dataController = DataController(p0!!)

        when(p1?.action){
            Intent.ACTION_SCREEN_OFF -> {
                lastTime = System.currentTimeMillis()
                if(!firstTime.equals(0)){
                    countTime = lastTime-firstTime
                    dataController!!.addTimeData("used", countTime, Date())
                    firstTime = lastTime
                }
            }
            Intent.ACTION_SCREEN_ON -> {
                lastTime = System.currentTimeMillis()
                if(!firstTime.equals(0)){
                    countTime = lastTime-firstTime
                    dataController!!.addTimeData("unused", countTime, Date())
                    firstTime = lastTime
                }
            }
        }
    }
}