package me.hyemdooly.sangs.dimigo.app.project

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class MainActivity : AppCompatActivity() {
    private val backgroundView by lazy { findViewById<WebView>(R.id.main_activity_background_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        backgroundView.visibility = View.INVISIBLE

        backgroundView.setBackgroundColor(Color.TRANSPARENT)

        initBackground()
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun initBackground() {
        backgroundView.loadUrl("file:///android_asset/background/index.html")

        val webSettings = backgroundView.settings
        webSettings.javaScriptEnabled = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.allowFileAccessFromFileURLs = true
            webSettings.allowUniversalAccessFromFileURLs = true
        }

        backgroundView.setLayerType(View.LAYER_TYPE_HARDWARE, null)
        backgroundView.webViewClient = object: WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                backgroundView.visibility = View.VISIBLE
            }
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase!!))
    }
}
