package com.leothos.goodmusic.data.repository

import com.leothos.goodmusic.data.database.entity.SongEntity
import kotlinx.coroutines.flow.Flow

class TestSongRepository : SongRepository {

    private val songs = populateSongs()

    override suspend fun insertOrUpdateSongs() {
        TODO("Not yet implemented")
    }

    override suspend fun markSongAsFavorite(id: Int, isFavorite: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun markSongsAsFavorite(albumId: Int, isFavorite: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getSongs(): Flow<List<SongEntity>> {
        TODO("Not yet implemented")
    }

    override fun getSongsFromAlbum(albumId: Int): Flow<List<SongEntity>> {
        TODO("Not yet implemented")
    }

    override fun getFavoriteSongs(): Flow<List<SongEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun clearAll() {
        TODO("Not yet implemented")
    }

    private fun populateSongs(): List<SongEntity> {
        val songs: MutableList<SongEntity> = mutableListOf()
        repeat(50) {
            songs.add(
                SongEntity(
                    albumId = it % 2,
                    id = it,
                    title = "title$it",
                    pictureUrl = "https://via.placeholder.com/600/f66b97",
                    thumbnailUrl = "https://via.placeholder.com/600/f66b97",
                    isFavorite = false
                )
            )
        }
        return songs
    }
}