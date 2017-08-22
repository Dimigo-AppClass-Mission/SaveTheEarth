package me.hyemdooly.sangs.dimigo.app.project

import android.app.Application
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class STEApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/NanumSquareRegular.ttf")
                .build())
    }
}