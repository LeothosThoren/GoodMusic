package com.leothos.goodmusic.feature.favoritesong

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.leothos.goodmusic.navigation.TopLevelDestination

fun NavGraphBuilder.favoriteScreen(isExpandedScreen: Boolean) {
    composable(route = TopLevelDestination.FAVORITE_ROUTE.name) {
        FavoriteRoute(
            viewModel = hiltViewModel(),
            isExpandedScreen = isExpandedScreen
        )
    }
}