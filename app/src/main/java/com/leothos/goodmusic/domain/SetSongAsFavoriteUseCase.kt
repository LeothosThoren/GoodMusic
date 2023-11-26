package com.leothos.goodmusic.domain

import com.leothos.goodmusic.data.repository.SongRepository
import javax.inject.Inject

class SetSongAsFavoriteUseCase @Inject constructor(
    private val songRepository: SongRepository
) {

    suspend operator fun invoke(songId: Int, isFavorite: Boolean) {
        songRepository.markSongAsFavorite(songId, isFavorite)
    }
}