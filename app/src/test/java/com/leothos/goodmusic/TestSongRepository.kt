package com.leothos.goodmusic

import com.leothos.goodmusic.data.repository.SongRepository
import com.leothos.goodmusic.model.Song

class TestSongRepository : SongRepository {

    private val songs = populateSongs()
    override suspend fun getSongs(): List<Song> {
        return songs.sortedBy { it.albumId }
    }

    private fun populateSongs(): List<Song> {
        val songs: MutableList<Song> = mutableListOf()
        repeat(50) {
            songs.add(
                Song(
                    albumId = it % 2,
                    id = it,
                    title = "title$it",
                    url = "https://via.placeholder.com/600/f66b97",
                    thumbnailUrl = "https://via.placeholder.com/600/f66b97"
                )
            )
        }
        return songs
    }
}