package com.leothos.goodmusic.ui

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.leothos.goodmusic.R
import com.leothos.goodmusic.data.util.NetworkMonitor
import com.leothos.goodmusic.navigation.GoodMusicNavGraph
import com.leothos.goodmusic.navigation.navigateToTopLevelDestination
import com.leothos.goodmusic.ui.component.AppBottomNavigationBar
import com.leothos.goodmusic.ui.component.AppNavRail


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GoodMusicApp(
    windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
    appState: GoodMusicAppState = rememberGoodMusicAppState(
        windowSizeClass = windowSizeClass,
        networkMonitor = networkMonitor
    )
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
        tonalElevation = 4.dp
    ) {

        val snackbarHostState = remember { SnackbarHostState() }
        val isOffline by appState.isOffline.collectAsStateWithLifecycle(false)

        val notConnexionMessage = stringResource(R.string.no_internet)
        LaunchedEffect(isOffline) {
            if (isOffline) {
                snackbarHostState.showSnackbar(
                    message = notConnexionMessage,
                    duration = SnackbarDuration.Indefinite,
                )
            }
        }

        Scaffold(
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            bottomBar = {
                if (!appState.isExpandedScreen) {
                    AppBottomNavigationBar(
                        destinations = appState.topLevelDestinations,
                        currentRoute = appState.currentDestination,
                        onNavigateToDestination = { destination ->
                            navigateToTopLevelDestination(
                                topLevelDestinations = destination,
                                appState.navController
                            )
                        },
                        modifier = Modifier.testTag("AppBottomNavigationBar")
                    )
                }
            }
        ) { padding ->
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .consumeWindowInsets(padding)
            ) {
                if (appState.isExpandedScreen) {
                    AppNavRail(
                        modifier = Modifier
                            .safeDrawingPadding()
                            .testTag("AppNavRail"),
                        destinations = appState.topLevelDestinations,
                        currentRoute = appState.currentDestination,
                        onNavigateToDestination = { destination ->
                            navigateToTopLevelDestination(
                                topLevelDestinations = destination,
                                navController = appState.navController
                            )
                        })
                }

                GoodMusicNavGraph(
                    navHostController = appState.navController
                )
            }
        }
    }
}