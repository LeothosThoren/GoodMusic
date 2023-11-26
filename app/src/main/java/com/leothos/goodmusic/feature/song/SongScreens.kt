package com.leothos.goodmusic.feature.song

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.leothos.goodmusic.feature.album.AlbumViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongListScreen(
    albumId: String,
    viewModel: AlbumViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    viewModel.getSongsFromAlbum(albumId.toInt())
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Album $albumId") },
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
        LazyColumn(modifier = Modifier.padding(padding)) {
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

@Composable
fun SongItem(
    id: Int,
    title: String,
    thumbnailUrl: String,
    isFavorite: Boolean,
    onFavoriteButtonClick: (Int, Boolean) -> Unit
) {
    Card {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            AsyncImage(model = thumbnailUrl, contentDescription = null)
            Text(text = title, style = MaterialTheme.typography.titleLarge)
            IconButton(onClick = {
                onFavoriteButtonClick(id, !isFavorite)
            }) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "cd_favorite_button"
                )
            }
        }
    }
}