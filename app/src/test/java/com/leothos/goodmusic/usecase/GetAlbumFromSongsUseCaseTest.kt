@file:OptIn(ExperimentalCoroutinesApi::class)

package com.leothos.goodmusic.usecase

import com.leothos.goodmusic.MainDispatcherRule
import com.leothos.goodmusic.data.mapper.toAlbum
import com.leothos.goodmusic.data.repository.SongRepository
import com.leothos.goodmusic.data.repository.SongRepositoryTest.FakeData
import com.leothos.goodmusic.domain.GetAlbumFromSongsUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class GetAlbumFromSongsUseCaseTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var songRepository: SongRepository

    @MockK
    private lateinit var useCase: GetAlbumFromSongsUseCase


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetAlbumFromSongsUseCase(songRepository)
    }

    @Test
    fun albumSongUseCase_whenSongEntitiesAreGiven_thenAlbumsAreReturned() = runTest {
        val songEntities = FakeData.generateFakeSongEntity()
        every { songRepository.getSongs() } returns flowOf(songEntities)

        val result = useCase.invoke().first()

        Assert.assertEquals(
            result.first(),
            songEntities.first().toAlbum()
        )
    }
}