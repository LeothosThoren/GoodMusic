package com.leothos.goodmusic.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.leothos.goodmusic.data.util.NetworkMonitor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Composable
fun rememberGoodMusicAppState(
    windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
): GoodMusicAppState {
    return remember(
        windowSizeClass,
        networkMonitor,
        coroutineScope,
        navController
    ) {
        GoodMusicAppState(
            windowSizeClass = windowSizeClass,
            networkMonitor = networkMonitor,
            coroutineScope = coroutineScope,
            navController = navController
        )
    }
}

@Stable
class GoodMusicAppState(
    val windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
    coroutineScope: CoroutineScope,
    val navController: NavHostController
) {

    val isExpandedScreen: Boolean
        get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )
}