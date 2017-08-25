package me.hyemdooly.sangs.dimigo.app.project.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.transitionseverywhere.TransitionManager

import me.hyemdooly.sangs.dimigo.app.project.R
import me.hyemdooly.sangs.dimigo.app.project.util.getNavigationHeight
import me.hyemdooly.sangs.dimigo.app.project.view.TextThumbProgressBar

/**
 * Created by dsa28s on 8/26/17.
 */

class MainFragment: Fragment() {
    private var rootView: View? = null

    private var mainContainer: RelativeLayout? = null
    var backgroundView: WebView? = null
    var characterView: ImageView? = null
    var levelText: TextView? = null
    var levelHumanReadableText: TextView? = null
    var bottomWidgetContainer: LinearLayout? = null
    var progressView: TextThumbProgressBar? = null
    var totalTimeText: TextView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(R.layout.fragment_main, null, false)

        mainContainer = rootView!!.findViewById(R.id.main_fragment_root_view)
        backgroundView = rootView!!.findViewById(R.id.main_fragment_background_view)
        characterView = rootView!!.findViewById(R.id.main_fragment_character_image)
        levelText = rootView!!.findViewById(R.id.main_fragment_level_text)
        levelHumanReadableText = rootView!!.findViewById(R.id.main_activity_level_humanreadable_text)
        bottomWidgetContainer = rootView!!.findViewById(R.id.main_fragment_bottom_widget_container)
        progressView = rootView!!.findViewById(R.id.main_fragment_progress)
        totalTimeText = rootView!!.findViewById(R.id.main_fragment_total_time_text)

        backgroundView!!.visibility = View.INVISIBLE
        backgroundView!!.setBackgroundColor(Color.TRANSPARENT)

        initBackground()
        checkNavigationBarAndAutoMargin(bottomWidgetContainer!!)

        progressView!!.setProgressTextColor(Color.parseColor("#270F30"))
        progressView!!.setProgressBarText("2시간 46분")

        return rootView!!
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initBackground() {
        backgroundView!!.loadUrl("file:///android_asset/background/index.html")

        val webSettings = backgroundView!!.settings
        webSettings.javaScriptEnabled = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.allowFileAccessFromFileURLs = true
            webSettings.allowUniversalAccessFromFileURLs = true
        }

        backgroundView!!.setLayerType(View.LAYER_TYPE_HARDWARE, null)
        backgroundView!!.webViewClient = object: WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                TransitionManager.beginDelayedTransition(mainContainer!!)
                backgroundView!!.visibility = View.VISIBLE
            }
        }
    }

    private fun checkNavigationBarAndAutoMargin(v: View) {
        if (v.layoutParams is ViewGroup.MarginLayoutParams) {
            val p = v.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(0, 0, 0, getNavigationHeight(activity))
            v.requestLayout()
        }
    }
}