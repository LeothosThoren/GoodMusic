package com.leothos.goodmusic.data.repository

import com.leothos.goodmusic.model.Song

interface SongRepository {
    suspend fun getSongs(): List<Song>
}