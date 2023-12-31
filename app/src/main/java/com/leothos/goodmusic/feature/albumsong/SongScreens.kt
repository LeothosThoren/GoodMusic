package com.leothos.goodmusic.feature.albumsong

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.leothos.goodmusic.R
import com.leothos.goodmusic.feature.album.AlbumViewModel
import com.leothos.goodmusic.ui.component.SongItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongListScreen(
    albumId: String,
    isExpandedScreen: Boolean,
    viewModel: AlbumViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    viewModel.getSongsFromAlbum(albumId.toInt())
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()
    val gridState = rememberLazyGridState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.album_quantity, albumId),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.headlineSmall
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }) { padding ->

        if (isExpandedScreen) {
            LazyVerticalGrid(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalArrangement = Arrangement.Center,
                contentPadding = padding,
                columns = GridCells.Fixed(2),
                state = gridState
            ) {
                items(items = uiState.value.songs, key = { it.id }) { song ->
                    SongItem(
                        id = song.id,
                        title = song.title,
                        thumbnailUrl = song.thumbnailUrl,
                        isFavorite = song.isFavorite,
                        onFavoriteButtonClick = viewModel::markSongAsFavorite
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.padding(padding),
                state = listState
            ) {
                items(items = uiState.value.songs, key = { it.id }) { song ->
                    SongItem(
                        id = song.id,
                        title = song.title,
                        thumbnailUrl = song.thumbnailUrl,
                        isFavorite = song.isFavorite,
                        onFavoriteButtonClick = viewModel::markSongAsFavorite
                    )
                }
            }
        }
    }
}