package com.blackfox.podlodka

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ExperimentalMotionApi
import com.blackfox.podlodka.data.AppData
import com.blackfox.podlodka.ui.theme.Background
import com.blackfox.podlodka.ui.theme.PodlodkaAnimationTheme
import com.blackfox.podlodka.utils.ThemeController

@ExperimentalMotionApi
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeController.applyTransparent(this)
        setContent {
            PodlodkaAnimationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Background
                ) {
                    DotaHomeworkCompose(AppData.testData)
                }
            }
        }
    }
}
