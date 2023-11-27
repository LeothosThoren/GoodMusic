package com.leothos.goodmusic.feature.favoritesong

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leothos.goodmusic.domain.GetGroupedFavoriteSongsUseCase
import com.leothos.goodmusic.domain.SetSongAsFavoriteUseCase
import com.leothos.goodmusic.model.Song
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteSongViewModel @Inject constructor(
    private val getGroupedFavoriteSongsUseCase: GetGroupedFavoriteSongsUseCase,
    private val setSongAsFavorite: SetSongAsFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoriteSongUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getGroupedFavoriteSongsUseCase.invoke()
                .catch { t ->
                    _uiState.update {
                        it.copy(errorMessage = t.message)
                    }
                }.collectLatest { favoriteGroupedSongs ->
                    _uiState.update {
                        it.copy(
                            favoriteSongs = favoriteGroupedSongs,
                            isEmpty = favoriteGroupedSongs.isEmpty()
                        )
                    }
                }
        }
    }

    fun markSongAsFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            setSongAsFavorite.invoke(id, isFavorite)
        }
    }
}

data class FavoriteSongUiState(
    val favoriteSongs: Map<Int, List<Song>> = emptyMap(),
    val isEmpty: Boolean = false,
    val errorMessage: String? = null
)

