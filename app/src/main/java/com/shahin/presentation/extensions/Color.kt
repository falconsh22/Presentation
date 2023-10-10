package com.shahin.presentation.extensions

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.core.graphics.ColorUtils

fun @receiver:ColorInt Int.isDark(): Boolean {
    val red = Color.red(this)
    val green = Color.green(this)
    val blue = Color.blue(this)

    val floats = FloatArray(3)
    ColorUtils.RGBToHSL(red, green, blue, floats)
    return floats[2] < 0.5f
}
