package me.hyemdooly.sangs.dimigo.app.project

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration
import me.hyemdooly.sangs.dimigo.app.project.database.Achieve
import me.hyemdooly.sangs.dimigo.app.project.database.DataController


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Realm.init(this)
        var realmConfiguration: RealmConfiguration = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(realmConfiguration)

        val test = getSharedPreferences("First", Context.MODE_PRIVATE)
        val user = getSharedPreferences("User", Context.MODE_PRIVATE)

        val firstData = test.getInt("First", 0)


        if(firstData == 0){
            val controller: DataController = DataController()
            var i = 1
            var achieve: Achieve = Achieve()
            while (i < 7){
                achieve.sequence = i
                achieve.categoryId = 1
                achieve.title = "레벨 "+i.toString()+" 달성"
                if(i == 1){
                    achieve.purpose = 3
                }else{
                    achieve.purpose = achieve.purpose?.times(2)
                }
                achieve.state = false

                controller.initAchieveData(achieve)
                Log.d("Ach", achieve.purpose.toString())
                i++
            }

            i = 1
            while (i < 5){
                achieve.sequence = i
                achieve.categoryId = 2

                if(i == 1){
                    achieve.purpose = 1
                }else{
                    achieve.purpose = achieve.purpose?.times(2)
                }
                achieve.title = achieve.purpose.toString()+"시간 동안 휴대폰 사용하지 않기"
                achieve.state = false

                controller.initAchieveData(achieve)
                Log.d("Ach", achieve.purpose.toString())
                i++
            }

            i = 1
            while (i < 5){
                achieve.sequence = i
                achieve.categoryId = 3
                if(i == 1){
                    achieve.purpose = 10
                }else if(i == 2){
                    achieve.purpose = 7
                }else{
                    achieve.purpose = achieve.purpose?.minus(2)
                }
                achieve.title = "하루동안 사용하는 앱 "+achieve.purpose.toString()+"개 이하"
                achieve.state = false

                controller.initAchieveData(achieve)
                Log.d("Ach", achieve.purpose.toString())
                i++
            }
            user.edit().putInt("Level", 1)
            user.edit().putLong("CurrentTime", 0)
            user.edit().putLong("TotalTime", 0)

            test.edit().putInt("First", 1).commit()
        }

        Handler().postDelayed(
                {
                    var intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()

        }, 3000)

    }


}


