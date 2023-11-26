package com.leothos.goodmusic.feature.favoritesong

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.leothos.goodmusic.ui.component.SongItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteSongList(
    uiState: FavoriteSongUiState,
    listState: LazyListState,
    onFavoriteButtonClick: (id: Int, isFavorite: Boolean) -> Unit
) {
    LazyColumn(state = listState) {
        uiState.favoriteSongs.forEach { (albumId, favoriteSongs) ->
            stickyHeader {
                Text(text = "Album n°$albumId")
            }

            items(items = favoriteSongs, key = { it.id }) {
                SongItem(
                    id = it.id,
                    title = it.title,
                    thumbnailUrl = it.thumbnailUrl,
                    isFavorite = it.isFavorite
                ) { id, isFavorite ->
                    onFavoriteButtonClick(id, isFavorite)
                }
            }
        }
    }
}

