package com.leothos.goodmusic.data.mapper

import com.leothos.goodmusic.data.database.entity.SongEntity
import com.leothos.goodmusic.data.network.dto.SongItemDto
import com.leothos.goodmusic.model.Album
import com.leothos.goodmusic.model.Song

fun SongItemDto.toSongEntity() =
    SongEntity(
        id = id,
        albumId = albumId,
        title = title,
        pictureUrl = url,
        thumbnailUrl = thumbnailUrl,
        isFavorite = false
    )

fun SongEntity.toSong() =
    Song(
        id = id,
        title = title,
        thumbnailUrl = thumbnailUrl,
        isFavorite = isFavorite
    )

fun SongEntity.toAlbum() =
    Album(
        albumId = albumId,
        pictureUrl = pictureUrl
    )
