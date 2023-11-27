package com.leothos.goodmusic.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.leothos.goodmusic.data.database.entity.SongEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {

    @Upsert
    suspend fun upsertSongEntities(songs: List<SongEntity>)

    @Query("UPDATE song_entity SET isFavorite=:isFavorite WHERE id=:id")
    suspend fun setOrUnsetSongEntityToFavorite(id: Int, isFavorite: Boolean): Int

    @Query("SELECT * FROM song_entity")
    fun getSongEntities(): Flow<List<SongEntity>>

    @Query("SELECT * FROM song_entity WHERE albumId = :albumId")
    fun getSongEntitiesFromAlbumId(albumId: Int): Flow<List<SongEntity>>

    @Query("SELECT * FROM song_entity WHERE isFavorite = 1")
    fun getFavoriteSongEntities(): Flow<List<SongEntity>>

    @Query("DELETE FROM song_entity")
    suspend fun clearSongEntities()
}