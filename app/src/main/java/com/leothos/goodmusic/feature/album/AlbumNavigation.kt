package com.leothos.goodmusic.feature.album

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.leothos.goodmusic.navigation.TopLevelDestination


const val ALBUM_ROUTE_GRAPH = "AlbumRouteGraph"

fun NavGraphBuilder.albumGraph(
    startDestination: String,
    onAlbumClick: (String) -> Unit,
    nestedGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = startDestination,
        startDestination = TopLevelDestination.ALBUM_ROUTE.name
    ) {
        composable(route = TopLevelDestination.ALBUM_ROUTE.name) {
            AlbumRoute { albumId ->
                onAlbumClick(albumId)
            }
        }

        nestedGraph()
    }
}