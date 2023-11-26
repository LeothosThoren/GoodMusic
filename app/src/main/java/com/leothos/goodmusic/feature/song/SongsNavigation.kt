package com.leothos.goodmusic.feature.song

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val albumIdArg = "albumId"
const val SONGS_ROUTE = "albumSongs_route"

fun NavGraphBuilder.songsScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = "$SONGS_ROUTE/{$albumIdArg}",
        arguments = listOf(navArgument(albumIdArg) { NavType.StringType })
    ) { backstackEntry ->

        val albumId = backstackEntry.arguments?.getString(albumIdArg)!!

        SongListScreen(
            albumId = albumId
        ) {
            onBackClick()
        }
    }

}