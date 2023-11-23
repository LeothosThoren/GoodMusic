package com.leothos.goodmusic.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.leothos.goodmusic.navigation.TopLevelDestination

@Composable
fun AppBottomNavigationBar(
    modifier: Modifier = Modifier,
    destinations: List<TopLevelDestination>,
    currentRoute: String?,
    onNavigateToDestination: (TopLevelDestination) -> Unit
) {

    NavigationBar {
        destinations.forEach { destination ->
            val selected = currentRoute == destination.name
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    Icon(
                        imageVector = if (selected) destination.selectedIcon else destination.unselectedIcon,
                        contentDescription = stringResource(destination.titleTextId),
                    )
                },
                alwaysShowLabel = false,
                modifier = modifier
            )
        }
    }
}

@Composable
fun AppNavRail(
    modifier: Modifier = Modifier,
    destinations: List<TopLevelDestination>,
    currentRoute: String?,
    onNavigateToDestination: (TopLevelDestination) -> Unit
) {

    NavigationRail {
        Spacer(Modifier.weight(1f))
        destinations.forEach { destination ->
            val selected = currentRoute == destination.name
            NavigationRailItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    Icon(
                        imageVector = if (selected) destination.selectedIcon else destination.unselectedIcon,
                        contentDescription = stringResource(destination.titleTextId),
                    )
                },
                alwaysShowLabel = false,
                modifier = modifier
            )
        }
        Spacer(Modifier.weight(1f))
    }
}