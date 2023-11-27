package com.leothos.goodmusic.feature.album

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.leothos.goodmusic.model.Album

@Composable
fun AlbumListScreen(
    innerPaddingValues: PaddingValues,
    albums: List<Album>,
    lazyGridState: LazyGridState,
    onAlbumClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalArrangement = Arrangement.Center,
        contentPadding = innerPaddingValues,
        columns = GridCells.Adaptive(240.dp),
        state = lazyGridState
    ) {
        items(items = albums, key = { album -> album.albumId }) {
            AlbumItem(url = it.pictureUrl, albumId = it.albumId) { albumId ->
                onAlbumClick(albumId)
            }
        }
    }
}

@Composable
private fun AlbumItem(
    url: String,
    albumId: Int,
    onAlbumClick: (Int) -> Unit
) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
            .aspectRatio(1f)
            .background(
                shape = MaterialTheme.shapes.extraLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
            .clip(MaterialTheme.shapes.extraLarge)
            .clickable {
                onAlbumClick(albumId)
            },
        contentAlignment = Alignment.Center,
    ) {
        AsyncImage(
            model = url,
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .padding(2.dp)
                .background(MaterialTheme.colorScheme.background),
            text = "Album: $albumId",
            style = MaterialTheme.typography.headlineSmall
        )
    }

}

@Preview(showBackground = true)
@Composable
fun AlbumItemPreview() {
    AlbumItem(url = "", albumId = 251) {

    }
}
