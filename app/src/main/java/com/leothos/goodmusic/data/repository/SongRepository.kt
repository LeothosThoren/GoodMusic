package com.leothos.goodmusic.data.repository

import com.leothos.goodmusic.data.database.entity.SongEntity

interface SongRepository {
    suspend fun getSongs(): List<SongEntity>
}