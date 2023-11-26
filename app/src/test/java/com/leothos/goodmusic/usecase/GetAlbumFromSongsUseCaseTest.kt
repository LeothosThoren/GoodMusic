@file:OptIn(ExperimentalCoroutinesApi::class)

package com.leothos.goodmusic.usecase

import com.leothos.goodmusic.MainDispatcherRule
import com.leothos.goodmusic.data.repository.TestSongRepository
import com.leothos.goodmusic.domain.GetAlbumFromSongsUseCase
import com.leothos.goodmusic.model.Album
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test


class GetAlbumFromSongsUseCaseTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val songRepository = TestSongRepository()

    val useCase = GetAlbumFromSongsUseCase(songRepository)

    @Test
    fun albumSongUseCase_albumsAreReturned() = runTest {
        val albums = useCase()

        songRepository.getSongs()

        //Assert.assertEquals()
    }

    private val testAlbums = listOf(
        Album(0, "https://via.placeholder.com/600/f66b97"),
        Album(1, "https://via.placeholder.com/600/f66b97")
    )
}