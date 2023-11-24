package com.leothos.goodmusic.data.mapper

import com.leothos.goodmusic.data.network.dto.SongItemDto
import com.leothos.goodmusic.model.Song

fun SongItemDto.toSong() =
    Song(
        albumId, id, title, url, thumbnailUrl
    )

