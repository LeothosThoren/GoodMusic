package com.leothos.goodmusic.feature.favoritesong

import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.leothos.goodmusic.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteRoute(
    viewModel: FavoriteSongViewModel,
    isExpandedScreen: Boolean
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val lazyListState = rememberLazyListState()
    val lazyGridState = rememberLazyGridState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(R.string.favorite_songs),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }, scrollBehavior = scrollBehavior
            )
        }, content = { padding ->
            FavoriteSongList(
                uiState = uiState.value,
                isExpandedScreen = isExpandedScreen,
                innerPaddingValues = padding,
                listState = lazyListState,
                gridState = lazyGridState,
                onFavoriteButtonClick = viewModel::markSongAsFavorite
            )
        })
}