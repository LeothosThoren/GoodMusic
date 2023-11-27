package com.leothos.goodmusic.feature.albumsong

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val albumIdArg = "albumId"
private const val SONGS_ROUTE = "albumSongs_route"

fun NavHostController.navigateToSongs(albumId: Int) {
    this.navigate(route = "$SONGS_ROUTE/$albumId")
}

fun NavGraphBuilder.songsScreen(
    isExpandedScreen: Boolean,
    onBackClick: () -> Unit
) {
    composable(
        route = "$SONGS_ROUTE/{$albumIdArg}",
        arguments = listOf(navArgument(albumIdArg) { NavType.StringType })
    ) { backstackEntry ->

        val albumId = backstackEntry.arguments?.getString(albumIdArg)!!

        SongListScreen(
            albumId = albumId,
            isExpandedScreen = isExpandedScreen
        ) {
            onBackClick()
        }
    }
}