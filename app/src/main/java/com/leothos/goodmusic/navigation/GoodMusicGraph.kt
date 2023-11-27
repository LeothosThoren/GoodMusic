package com.leothos.goodmusic.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.leothos.goodmusic.feature.album.albumGraph
import com.leothos.goodmusic.feature.albumsong.navigateToSongs
import com.leothos.goodmusic.feature.albumsong.songsScreen
import com.leothos.goodmusic.feature.favoritesong.favoriteScreen


private const val ALBUM_ROUTE_GRAPH = "AlbumRouteGraph"

@Composable
fun GoodMusicNavGraph(
    modifier: Modifier = Modifier,
    isExpandedScreen: Boolean,
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = ALBUM_ROUTE_GRAPH,
        modifier = modifier
    ) {

        albumGraph(
            graphRoute = ALBUM_ROUTE_GRAPH,
            destination = TopLevelDestination.ALBUM_ROUTE.name,
            onAlbumClick = { albumId ->
                navHostController.navigateToSongs(albumId)
            }) {
            songsScreen(isExpandedScreen = isExpandedScreen) {
                navHostController.popBackStack()
            }
        }

        favoriteScreen(isExpandedScreen = isExpandedScreen)
    }
}