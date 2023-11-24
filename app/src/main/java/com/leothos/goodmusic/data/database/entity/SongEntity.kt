package com.leothos.goodmusic.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "song_entity")
data class SongEntity(
    @PrimaryKey
    val id: Int,
    val albumId: Int,
    val title: String,
    val thumbnailUrl: String,
    val pictureUrl: String,
    val isFavorite: Boolean
)