package com.leothos.goodmusic.feature.favorite

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteSongList() {
    LazyColumn {
        items(count = 50) {
            Row(Modifier.padding(16.dp)) {
                Text("Favorite $it")
            }
        }
    }
}