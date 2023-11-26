package com.leothos.goodmusic.feature.album

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation


fun NavGraphBuilder.albumGraph(
    graphRoute: String,
    destination: String,
    onAlbumClick: (Int) -> Unit,
    nestedGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = graphRoute,
        startDestination = destination
    ) {
        composable(route = destination) {
            AlbumRoute { albumId ->
                onAlbumClick(albumId)
            }
        }

        nestedGraph()
    }
}