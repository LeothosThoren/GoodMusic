package com.leothos.goodmusic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.leothos.goodmusic.data.util.NetworkMonitor
import com.leothos.goodmusic.ui.GoodMusicApp
import com.leothos.goodmusic.ui.theme.GoodMusicTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContent {
            GoodMusicTheme {
                GoodMusicApp(
                    windowSizeClass = calculateWindowSizeClass(activity = this),
                    networkMonitor = networkMonitor
                )
            }
        }
    }
}