package com.leothos.goodmusic.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import com.leothos.goodmusic.R

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val titleTextId: Int,
) {
    ALBUM_ROUTE(
        selectedIcon = Icons.Rounded.PlayArrow,
        unselectedIcon = Icons.Outlined.PlayArrow,
        titleTextId = R.string.album,
    ),
    FAVORITE_ROUTE(
        selectedIcon = Icons.Rounded.Favorite,
        unselectedIcon = Icons.Outlined.FavoriteBorder,
        titleTextId = R.string.favorite,
    ),
}

fun navigateToTopLevelDestination(
    topLevelDestinations: TopLevelDestination,
    navController: NavHostController
) {
    val topLevelNavOptions = navOptions {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

    when (topLevelDestinations) {
        TopLevelDestination.ALBUM_ROUTE -> navController.navigate(
            topLevelDestinations.name,
            topLevelNavOptions
        )

        TopLevelDestination.FAVORITE_ROUTE -> navController.navigate(
            topLevelDestinations.name,
            topLevelNavOptions
        )
    }
}