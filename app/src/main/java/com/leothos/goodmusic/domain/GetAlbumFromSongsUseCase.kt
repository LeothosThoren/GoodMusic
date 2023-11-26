package com.leothos.goodmusic.domain

import com.leothos.goodmusic.data.repository.SongRepository
import com.leothos.goodmusic.model.Album
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAlbumFromSongsUseCase @Inject constructor(
    private val songRepository: SongRepository
) {
    /**
     * Returns a list of album from Song model
     */
    operator fun invoke(): Flow<List<Album>> {
        return songRepository.getSongs()
            .map { songs ->
                songs.groupBy { song ->
                    song.albumId
                }.map {
                    Album(it.key, it.value.first().pictureUrl)
                }
            }
    }
}

