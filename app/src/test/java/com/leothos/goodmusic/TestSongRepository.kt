package com.leothos.goodmusic

import com.leothos.goodmusic.data.database.entity.SongEntity
import com.leothos.goodmusic.data.repository.SongRepository

class TestSongRepository : SongRepository {

    private val songs = populateSongs()
    override suspend fun getSongs(): List<SongEntity> {
        return songs.sortedBy { it.albumId }
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