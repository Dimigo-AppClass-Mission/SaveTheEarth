package me.hyemdooly.sangs.dimigo.app.project.database

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import io.realm.Realm
import java.util.*

/**
 * Created by songhyemin on 2017. 8. 23..
 */
class DataController {
    var realm: Realm = Realm.getDefaultInstance()
    var user: SharedPreferences? = null
    var userTotalTime : Long = 0.toLong() // 안쓴 총 시간
    var userTotalTime2 : Long = 0.toLong()
//    var purposeAchieve1 = realm.where(Achieve::class.java).equalTo("state", false).equalTo("categoryId", 1).findFirst()
//    var purposeAchieve2 = realm.where(Achieve::class.java).equalTo("state", false).equalTo("categoryId", 2).findFirst()

    constructor(context: Context) {
        user = context.getSharedPreferences("User", Context.MODE_PRIVATE)
        userTotalTime = user!!.getLong("TotalTime", 0)
    }

    fun addTimeData(dataName: String, millis: Long, date: Date){
        when(dataName){
            "used" -> {
                var timeUsed: TimeUsed = TimeUsed()
                timeUsed.date = date
                timeUsed.time = (millis/1000)
                userTotalTime2 += timeUsed.time as Long
                user!!.edit().putLong("UnTotalTime", userTotalTime2).apply()
                realm.beginTransaction()
                realm.copyToRealm(timeUsed)
                realm.commitTransaction()
                Log.d("data_used", timeUsed.time.toString())
            }

            "unused" -> {
                var timeUnUsed: TimeUnUsed = TimeUnUsed()
                timeUnUsed.date = date
                timeUnUsed.time = (millis/1000)
                userTotalTime += timeUnUsed.time as Long
                user!!.edit().putLong("TotalTime", userTotalTime).apply()
                Log.d("total_unusedData", (user as SharedPreferences).getLong("TotalTime", 0).toString())
                realm.beginTransaction()
                realm.copyToRealm(timeUnUsed)
                realm.commitTransaction()
                Log.d("data_unused", timeUnUsed.time.toString())

            }

        }

    }

    fun initAchieveData(achieve : Achieve){
        realm.beginTransaction()
        realm.copyToRealm(achieve)
        realm.commitTransaction()
    }
}