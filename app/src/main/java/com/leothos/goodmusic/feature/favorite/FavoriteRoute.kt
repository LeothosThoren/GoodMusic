package com.leothos.goodmusic.feature.favorite

import androidx.compose.runtime.Composable
import com.leothos.goodmusic.feature.album.AlbumViewModel

@Composable
fun FavoriteRoute(viewModel: AlbumViewModel) {
    FavoriteSongList()
}