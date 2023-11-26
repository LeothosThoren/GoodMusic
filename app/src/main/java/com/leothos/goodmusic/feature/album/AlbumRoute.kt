package com.leothos.goodmusic.feature.album

import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun AlbumRoute(
    viewModel: AlbumViewModel = hiltViewModel(),
    onAlbumClick: (String) -> Unit
) {
    viewModel.getAlbumsFromSongs()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val gridState = rememberLazyGridState()

    AlbumListScreen(
        albums = uiState.value.albums,
        lazyGridState = gridState,
        onAlbumClick
    )
}