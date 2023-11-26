package com.leothos.goodmusic.domain

import com.leothos.goodmusic.data.mapper.toSong
import com.leothos.goodmusic.data.repository.SongRepository
import com.leothos.goodmusic.model.Song
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetGroupedFavoriteSongsUseCase @Inject constructor(
    private val songRepository: SongRepository
) {
    /**
     * Returns a map of favorite songs with album id as key
     */

    operator fun invoke(): Flow<Map<Int, List<Song>>> {
        return songRepository.getFavoriteSongs()
            .map { favoriteSong ->
                favoriteSong.groupBy { it.albumId }
                    .mapValues { group ->
                        group.value.map { songEntity ->
                            songEntity.toSong()
                        }
                    }
            }
    }
}