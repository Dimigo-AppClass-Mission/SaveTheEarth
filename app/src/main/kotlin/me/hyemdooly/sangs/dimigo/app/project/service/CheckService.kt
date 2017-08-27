package me.hyemdooly.sangs.dimigo.app.project.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmResults
import me.hyemdooly.sangs.dimigo.app.project.database.Achieve
import me.hyemdooly.sangs.dimigo.app.project.database.TimeUnUsed
import me.hyemdooly.sangs.dimigo.app.project.database.TimeUsed


/**
 * Created by songhyemin on 2017. 8. 27..
 */
class CheckService() : Service() {

    var realm: Realm = Realm.getDefaultInstance()
    var achieve: RealmResults<Achieve>? = realm.where(Achieve::class.java).findAll()
    var TimeUnUsed: RealmResults<TimeUnUsed>? = realm.where(me.hyemdooly.sangs.dimigo.app.project.database.TimeUnUsed::class.java).findAll()
    var TimeUsed: RealmResults<TimeUsed>? = realm.where(me.hyemdooly.sangs.dimigo.app.project.database.TimeUsed::class.java).findAll()



    override fun onCreate() {
        super.onCreate()


        achieve?.addChangeListener(RealmChangeListener<RealmResults<Achieve>> {
            // notify!
            Log.d("change", realm.where(Achieve::class.java).findFirst().title.toString())
        })


    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



}