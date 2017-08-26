package me.hyemdooly.sangs.dimigo.app.project.util

import android.content.res.Resources
import android.util.TypedValue

fun convertIntegerToPx(res: Resources, int: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, int, res.displayMetrics)
}

fun convertIntegerToDp(res: Resources, int: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, int, res.displayMetrics)
}