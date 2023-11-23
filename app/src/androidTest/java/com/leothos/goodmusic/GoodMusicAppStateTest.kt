package com.leothos.goodmusic

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import androidx.navigation.testing.TestNavHostController
import com.leothos.goodmusic.ui.GoodMusicAppState
import com.leothos.goodmusic.ui.rememberGoodMusicAppState
import com.leothos.goodmusic.util.TestNetworkMonitor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

class GoodMusicAppStateTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    // Create the test dependencies.
    private val networkMonitor = TestNetworkMonitor()

    // Subject under test.
    private lateinit var state: GoodMusicAppState

    @Test
    fun appState_currentDestination() = runTest {
        var currentDestination: String? = null

        composeTestRule.setContent {
            val navController = rememberTestNavController()
            val coroutineScope = rememberCoroutineScope()
            state = remember(navController) {
                GoodMusicAppState(
                    windowSizeClass = getCompactWindowClass(),
                    networkMonitor = networkMonitor,
                    navController = navController,
                    coroutineScope = coroutineScope
                )
            }

            // Update currentDestination whenever it changes
            currentDestination = state.currentDestination

            // Navigate to destination b once
            LaunchedEffect(Unit) {
                navController.setCurrentDestination("b")
            }
        }

        assertEquals("b", currentDestination)
    }

    @Test
    fun appState_destinations() = runTest {
        composeTestRule.setContent {
            state = rememberGoodMusicAppState(
                windowSizeClass = getCompactWindowClass(),
                networkMonitor = networkMonitor
            )
        }

        assertEquals(2, state.topLevelDestinations.size)
        assertTrue(state.topLevelDestinations[0].name.contains("album_route", true))
        assertTrue(state.topLevelDestinations[1].name.contains("favorite_route", true))
    }

    @Test
    fun appState_isCompact() = runTest {
        composeTestRule.setContent {
            val navController = rememberTestNavController()
            val coroutineScope = rememberCoroutineScope()
            state = GoodMusicAppState(
                windowSizeClass = getCompactWindowClass(),
                networkMonitor = networkMonitor,
                navController = navController,
                coroutineScope = coroutineScope
            )
        }

        assertFalse(state.isExpandedScreen)
    }

    @Test
    fun appState_isExpanded() = runTest {
        composeTestRule.setContent {
            val navController = rememberTestNavController()
            val coroutineScope = rememberCoroutineScope()
            state = GoodMusicAppState(
                windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(1200.dp, 900.dp)),
                networkMonitor = networkMonitor,
                navController = navController,
                coroutineScope = coroutineScope
            )
        }

        assertTrue(state.isExpandedScreen)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun stateIsOfflineWhenNetworkMonitorIsOffline() = runTest(UnconfinedTestDispatcher()) {
        composeTestRule.setContent {
            val navController = rememberTestNavController()
            val coroutineScope = rememberCoroutineScope()
            state = remember(navController) {
                GoodMusicAppState(
                    windowSizeClass = getCompactWindowClass(),
                    networkMonitor = networkMonitor,
                    navController = navController,
                    coroutineScope = coroutineScope
                )
            }

            backgroundScope.launch { state.isOffline.collect() }
            networkMonitor.setConnected(false)
            assertEquals(
                true,
                state.isOffline.value,
            )
        }
    }

    @Composable
    private fun rememberTestNavController(): TestNavHostController {
        val context = LocalContext.current
        return remember {
            TestNavHostController(context).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
                graph = createGraph(startDestination = "a") {
                    composable("a") { }
                    composable("b") { }
                    composable("c") { }
                }
            }
        }
    }

    private fun getCompactWindowClass() = WindowSizeClass.calculateFromSize(DpSize(500.dp, 300.dp))

}