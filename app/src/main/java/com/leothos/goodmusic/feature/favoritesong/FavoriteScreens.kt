package com.leothos.goodmusic.feature.favoritesong

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.leothos.goodmusic.ui.component.SongItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteSongList(
    uiState: FavoriteSongUiState,
    isExpandedScreen: Boolean,
    innerPaddingValues: PaddingValues,
    listState: LazyListState,
    gridState: LazyGridState,
    onFavoriteButtonClick: (id: Int, isFavorite: Boolean) -> Unit
) {
    if (isExpandedScreen) {
        LazyVerticalGrid(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalArrangement = Arrangement.Center,
            contentPadding = innerPaddingValues,
            columns = GridCells.Fixed(2),
            state = gridState
        ) {
            uiState.favoriteSongs.forEach { (albumId, favoriteSongs) ->
                item {
                    SongHeader(albumId)
                }

                items(items = favoriteSongs, key = { it.id }) { song ->
                    SongItem(
                        id = song.id,
                        title = song.title,
                        thumbnailUrl = song.thumbnailUrl,
                        isFavorite = song.isFavorite,
                        onFavoriteButtonClick = onFavoriteButtonClick
                    )
                }
            }
        }
    } else {
        LazyColumn(contentPadding = innerPaddingValues, state = listState) {
            uiState.favoriteSongs.forEach { (albumId, favoriteSongs) ->
                stickyHeader {
                    SongHeader(albumId = albumId)
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
}

@Composable
private fun SongHeader(albumId: Int) {
    Row(Modifier.padding(start = 24.dp), horizontalArrangement = Arrangement.Center) {
        Text(
            text = "Album n°$albumId",
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

