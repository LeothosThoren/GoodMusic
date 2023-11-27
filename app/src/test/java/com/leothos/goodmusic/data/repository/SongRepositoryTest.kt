package com.leothos.goodmusic.data.repository

import com.leothos.goodmusic.data.database.entity.SongEntity
import com.leothos.goodmusic.data.repository.SongRepositoryTest.FakeData.generateFakeSongEntity
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SongRepositoryTest {

    @MockK
    private lateinit var songRepository: SongRepository

    @Before
    fun init() {
        MockKAnnotations.init(this)
    }

    @Test
    fun songRepository_getSongs_return_something() = runTest {
        every { songRepository.getSongs() } returns flowOf(generateFakeSongEntity())
        val result = songRepository.getSongs()

        assertEquals(result.first(), generateFakeSongEntity())
    }

    @Test
    fun songRepository_getSongs_return_nothing() = runTest {
        every { songRepository.getSongs() } returns flowOf(emptyList())
        val result = songRepository.getSongs()

        assertTrue(result.first().isEmpty())
    }

    @Test
    fun songRepository_getSongsFromId_return_something() = runTest {
        every { songRepository.getSongsFromAlbum(any()) } returns flowOf(generateFakeSongEntity().filter {
            it.albumId == 0
        })

        val result = songRepository.getSongsFromAlbum(0)

        assertTrue(result.first().size == 5)

    }

    @Test
    fun songRepository_getSongsFromId_return_nothing() = runTest {
        every { songRepository.getSongsFromAlbum(any()) } returns flowOf(
            emptyList()
        )

        val result = songRepository.getSongsFromAlbum(0)

        assertTrue(result.first().isEmpty())
    }

    @Test
    fun songRepository_getFavoriteSongs_return_favorites() = runTest {
        every { songRepository.getFavoriteSongs() } returns flowOf(
            generateFakeSongEntity().filter {
                it.isFavorite
            })

        val result = songRepository.getFavoriteSongs()

        assertTrue(result.first().size == 5)
    }

    @Test
    fun songRepository_getFavoriteSongs_return_no_favorites() = runTest {
        every { songRepository.getFavoriteSongs() } returns flowOf(emptyList())

        val result = songRepository.getFavoriteSongs()

        assertTrue(result.first().isEmpty())
    }

    object FakeData {
        fun generateFakeSongEntity(): List<SongEntity> {
            val list = mutableListOf<SongEntity>()
            (1..10).forEach {

                list.add(
                    SongEntity(
                        id = it,
                        albumId = it % 2,
                        title = "Title$it",
                        thumbnailUrl = "",
                        pictureUrl = "",
                        isFavorite = it % 2 == 0
                    )
                )

            }
            return list
        }
    }

}