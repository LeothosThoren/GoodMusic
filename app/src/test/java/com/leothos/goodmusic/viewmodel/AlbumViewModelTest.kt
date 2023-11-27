package com.leothos.goodmusic.viewmodel

import com.leothos.goodmusic.MainDispatcherRule
import com.leothos.goodmusic.data.repository.SongRepository
import com.leothos.goodmusic.data.repository.SongRepositoryTest.FakeData
import com.leothos.goodmusic.domain.GetAlbumFromSongsUseCase
import com.leothos.goodmusic.domain.SetSongAsFavoriteUseCase
import com.leothos.goodmusic.feature.album.AlbumUiState
import com.leothos.goodmusic.feature.album.AlbumViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AlbumViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var songRepository: SongRepository

    @MockK
    private lateinit var getAlbumUSeCase: GetAlbumFromSongsUseCase

    @MockK
    private lateinit var setSongAsFavoriteUseCase: SetSongAsFavoriteUseCase

    private lateinit var viewModel: AlbumViewModel

    @Before
    fun init() {
        MockKAnnotations.init(this)
        viewModel = AlbumViewModel(songRepository, getAlbumUSeCase, setSongAsFavoriteUseCase)
    }

    @Test
    fun uiState_whenInitialized_thenShowLoading() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        assertEquals(AlbumUiState().isLoading, viewModel.uiState.value.isLoading)

        collectJob.cancel()
    }

    @Test
    fun uiState_whenGetAlbums_thenShowResult() = runTest {
        coEvery { songRepository.insertOrUpdateSongs() } answers { }
        every { songRepository.getSongs() } returns flowOf(FakeData.generateFakeSongEntity())
        viewModel.getAlbumsFromSongs()


        val uiState = viewModel.uiState.value

        assertTrue(uiState.albums.isNotEmpty())
    }

    @Test
    fun uiState_whenGetSongsFromAlbumId_thenShowResult() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }
        every { songRepository.getSongsFromAlbum(any()) } returns flowOf(
            FakeData.generateFakeSongEntity().filter { it.albumId == 0 })

        viewModel.getSongsFromAlbum(1)

        assertEquals(AlbumUiState().songs, viewModel.uiState.value.songs)
        collectJob.cancel()
    }
}