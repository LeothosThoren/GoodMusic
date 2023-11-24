package com.leothos.goodmusic.model

data class Song(
    val id: Int,
    val title: String,
    val thumbnailUrl: String,
    val isFavorite: Boolean
)