package com.leothos.goodmusic.viewmodel

import com.leothos.goodmusic.MainDispatcherRule
import com.leothos.goodmusic.domain.GetGroupedFavoriteSongsUseCase
import com.leothos.goodmusic.domain.SetSongAsFavoriteUseCase
import com.leothos.goodmusic.feature.favoritesong.FavoriteSongUiState
import com.leothos.goodmusic.feature.favoritesong.FavoriteSongViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FavoriteViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var getGroupedFavoriteSongsUseCase: GetGroupedFavoriteSongsUseCase

    @MockK
    private lateinit var setSongAsFavoriteUseCase: SetSongAsFavoriteUseCase

    private lateinit var viewModel: FavoriteSongViewModel

    @Before
    fun init() {
        MockKAnnotations.init(this)
        viewModel = FavoriteSongViewModel(getGroupedFavoriteSongsUseCase, setSongAsFavoriteUseCase)
    }

    @Test
    fun uiState_whenInitialized_showState() = runTest {

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        assertEquals(FavoriteSongUiState().isEmpty, viewModel.uiState.value.isEmpty)
        assertEquals(FavoriteSongUiState().favoriteSongs, viewModel.uiState.value.favoriteSongs)

        collectJob.cancel()
    }
}