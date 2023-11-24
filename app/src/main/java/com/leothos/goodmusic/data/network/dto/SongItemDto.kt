package com.leothos.goodmusic.data.network.dto


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SongItemDto(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)