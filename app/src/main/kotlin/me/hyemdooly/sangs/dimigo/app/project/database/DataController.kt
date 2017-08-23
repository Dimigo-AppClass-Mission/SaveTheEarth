package me.hyemdooly.sangs.dimigo.app.project.database

import android.util.Log
import io.realm.Realm
import java.util.*

/**
 * Created by songhyemin on 2017. 8. 23..
 */
class DataController {
    var realm: Realm = Realm.getDefaultInstance()

    // var settingList: RealmResults<Setting>? = realm.where(Setting::class.java).findAll()

    fun addTimeData(dataName: String, millis: Long, date: Date){
        when(dataName){
            "used" -> {
                var timeUsed: TimeUsed = TimeUsed()
                timeUsed.date = date
                timeUsed.time = millis
                realm.beginTransaction()
                realm.copyToRealm(timeUsed)
                realm.commitTransaction()
                Log.d("data", realm.where(TimeUsed::class.java).findFirst().toString())

            }

            "unused" -> {
                var timeUnUsed: TimeUnUsed = TimeUnUsed()
                timeUnUsed.date = date
                timeUnUsed.time = millis
                realm.beginTransaction()
                realm.copyToRealm(timeUnUsed)
                realm.commitTransaction()
                Log.d("data", realm.where(TimeUnUsed::class.java).findFirst().toString())
            }
        }

    }


}