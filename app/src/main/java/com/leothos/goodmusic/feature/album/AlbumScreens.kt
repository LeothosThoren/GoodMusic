package com.leothos.goodmusic.feature.album

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.leothos.goodmusic.model.Album

@Composable
fun AlbumListScreen(
    albums: List<Album>,
    lazyGridState: LazyGridState,
    onAlbumClick: (Int) -> Unit
) {
    LazyVerticalGrid(columns = GridCells.Adaptive(150.dp), state = lazyGridState) {
        items(items = albums, key = { album -> album.albumId }) {
            AlbumItem(url = it.pictureUrl, albumId = it.albumId) { albumId ->
                onAlbumClick(albumId)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AlbumItem(url: String, albumId: Int, onAlbumClick: (Int) -> Unit) {
    Card(onClick = { onAlbumClick(albumId) }) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier.size(height = 200.dp, width = 100.dp),
                model = url,
                contentDescription = null
            )
            Text(text = "Album: $albumId")
        }
    }
}
