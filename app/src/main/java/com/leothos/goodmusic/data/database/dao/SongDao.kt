package com.leothos.goodmusic.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.leothos.goodmusic.data.database.entity.SongEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {
    @Upsert
    suspend fun insertOrUpdateSongEntities(songs: List<SongEntity>)

    @Query("SELECT * FROM song_entity WHERE albumId = :albumId")
    fun getSongEntitiesFromAlbumId(albumId: Int): Flow<List<SongEntity>>

    @Query("SELECT * FROM song_entity WHERE isFavorite = 1")
    fun getFavoriteSongEntities(): Flow<List<SongEntity>>

    @Query("DELETE FROM song_entity")
    suspend fun clearSongEntities()
}