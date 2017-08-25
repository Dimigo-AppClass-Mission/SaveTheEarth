package me.hyemdooly.sangs.dimigo.app.project.util

import android.content.Context
import android.view.KeyCharacterMap
import android.view.KeyEvent
import android.view.ViewConfiguration
import android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
import android.app.Activity
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View


/**
 * Created by dsa28s on 8/26/17.
 */

fun getNavigationHeight(context: Context): Int {
    val resources = context.getResources()
    val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")

    return if(isNavigationBarUsed(context)) {
        if (resourceId > 0) {
            resources.getDimensionPixelSize(resourceId)
        } else 0
    } else {
        0
    }
}

fun isNavigationBarUsed(context: Context): Boolean {
    val hasMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey()
    val hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK)

    return !hasMenuKey && !hasBackKey
}

@RequiresApi(api = Build.VERSION_CODES.M)
fun setSystemBarTheme(view: View, pIsDark: Boolean) {
    if(!pIsDark) {
        view.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    } else {
        view.systemUiVisibility = 0
    }


    //val lFlags = pActivity.window.decorView.systemUiVisibility
    //pActivity.window.decorView.systemUiVisibility = if (pIsDark) lFlags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv() else lFlags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
}

fun getStatusBarHeight(context: Context): Int {
    var result = 0
    val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = context.resources.getDimensionPixelSize(resourceId)
    }
    return result
}