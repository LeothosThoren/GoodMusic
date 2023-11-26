package com.leothos.goodmusic.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun SongItem(
    id: Int,
    title: String,
    thumbnailUrl: String,
    isFavorite: Boolean,
    onFavoriteButtonClick: (Int, Boolean) -> Unit
) {
    var checked by remember { mutableStateOf(isFavorite) }

    ElevatedCard {
        val roundShape = RoundedCornerShape(100)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .size(54.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = roundShape
                    )
                    .border(
                        BorderStroke(1.dp, color = MaterialTheme.colorScheme.onTertiary),
                        shape = roundShape
                    )

            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(roundShape),
                    contentScale = ContentScale.Fit,
                    model = thumbnailUrl,
                    contentDescription = null
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                overflow = TextOverflow.Ellipsis
            )
            IconToggleButton(checked = checked, onCheckedChange = {
                checked = it
                onFavoriteButtonClick(id, checked)
            }) {
                Icon(
                    modifier = Modifier.size(36.dp),
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "cd_favorite_button",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview
@Composable
fun SongListItemPreview() {
    SongItem(
        id = 1,
        title = "This is a title",
        thumbnailUrl = "",
        isFavorite = false
    ) { id, favortie ->
        {}
    }
}