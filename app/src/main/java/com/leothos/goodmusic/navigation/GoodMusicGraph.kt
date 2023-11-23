package com.leothos.goodmusic.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


private const val ALBUM_ROUTE_GRAPH = "AlbumRouteGraph"

@Composable
fun GoodMusicNavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    startDestination: String = ALBUM_ROUTE_GRAPH
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = startDestination) {}
    }
}