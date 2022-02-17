package com.blackfox.podlodka.utils

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import com.blackfox.podlodka.R

object ThemeController {

    private const val MIN_BRIGHTNESS_NAVIGATION_BAR_COLOR = 0.4f

    fun colorNavigationBar(activity: Activity, color: Int) {
        with(activity.window) {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            navigationBarColor = color
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                var systemUiVisibility = activity.window.decorView.systemUiVisibility
                val flagLightNavigationBar = View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                systemUiVisibility = if (ColorUtils.calculateLuminance(color) > MIN_BRIGHTNESS_NAVIGATION_BAR_COLOR) {
                    systemUiVisibility or flagLightNavigationBar
                } else {
                    systemUiVisibility and flagLightNavigationBar.inv()
                }
                activity.window.decorView.systemUiVisibility = systemUiVisibility
            }
        }
    }

    private fun transparentStatusBar(activity: Activity) {
        with(activity.window) {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            statusBarColor = Color.TRANSPARENT
        }
    }

    fun applyTransparent(activity: Activity) {
        val backgroundColor = ContextCompat.getColor(activity, R.color.background)
        colorNavigationBar(activity, backgroundColor)
        transparentStatusBar(activity)
    }
}

