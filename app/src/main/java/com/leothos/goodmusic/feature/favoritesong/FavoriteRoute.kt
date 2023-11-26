package com.leothos.goodmusic.feature.favoritesong

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun FavoriteRoute(viewModel: FavoriteSongViewModel) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val lazyListState = rememberLazyListState()

    FavoriteSongList(
        uiState = uiState.value,
        listState = lazyListState,
        onFavoriteButtonClick = viewModel::markSongAsFavorite
    )
}