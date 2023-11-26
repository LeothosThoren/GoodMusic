package com.leothos.goodmusic.data.repository

import com.leothos.goodmusic.data.database.entity.SongEntity
import kotlinx.coroutines.flow.Flow

interface SongRepository {

    suspend fun insertOrUpdateSongs()

    suspend fun markSongAsFavorite(id: Int, isFavorite: Boolean)

    suspend fun markSongsAsFavorite(albumId: Int, isFavorite: Boolean)

    fun getSongs(): Flow<List<SongEntity>>

    fun getSongsFromAlbum(albumId: Int): Flow<List<SongEntity>>

    fun getFavoriteSongs(): Flow<List<SongEntity>>

    suspend fun clearAll()

}