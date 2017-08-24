package me.hyemdooly.sangs.dimigo.app.project

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import io.realm.Realm
import io.realm.RealmConfiguration

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Realm.init(this)
        var realmConfiguration: RealmConfiguration = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(realmConfiguration)




        Handler().postDelayed(
                {
                    var intent = Intent(this@SplashActivity, DetailActivity::class.java)
                    startActivity(intent)
                    finish()

        }, 3000)

    }


}


