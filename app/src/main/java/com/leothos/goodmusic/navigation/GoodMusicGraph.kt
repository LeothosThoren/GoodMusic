package com.leothos.goodmusic.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.leothos.goodmusic.feature.album.ALBUM_ROUTE_GRAPH
import com.leothos.goodmusic.feature.album.albumGraph
import com.leothos.goodmusic.feature.favorite.favoriteScreen
import com.leothos.goodmusic.feature.song.SONGS_ROUTE
import com.leothos.goodmusic.feature.song.songsScreen


@Composable
fun GoodMusicNavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = ALBUM_ROUTE_GRAPH,
        modifier = modifier
    ) {

        albumGraph(
            startDestination = ALBUM_ROUTE_GRAPH,
            onAlbumClick = { albumId ->
                navHostController.navigate(route = "$SONGS_ROUTE/$albumId")
            }) {
            songsScreen {
                navHostController.popBackStack()
            }
        }

        favoriteScreen()
    }
}