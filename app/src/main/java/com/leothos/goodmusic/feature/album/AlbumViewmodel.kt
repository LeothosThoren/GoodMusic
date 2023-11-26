package com.leothos.goodmusic.feature.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leothos.goodmusic.data.mapper.toSong
import com.leothos.goodmusic.data.repository.SongRepository
import com.leothos.goodmusic.domain.GetAlbumFromSongsUseCase
import com.leothos.goodmusic.domain.SetSongAsFavoriteUseCase
import com.leothos.goodmusic.model.Album
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
class AlbumViewModel @Inject constructor(
    private val songRepository: SongRepository,
    private val getAlbum: GetAlbumFromSongsUseCase,
    private val setSongAsFavorite: SetSongAsFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AlbumUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                songRepository.insertOrUpdateSongs()
            } catch (t: Throwable) {
                print(t.message)
            }
        }
    }

    fun getAlbumsFromSongs() {
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(isLoading = true)
                }
                getAlbum.invoke().collectLatest { albums ->
                    _uiState.update {
                        it.copy(albums = albums, isEmpty = albums.isEmpty())
                    }
                }
            } catch (t: Throwable) {
                _uiState.update {
                    it.copy(errorMessage = t.message)
                }
            } finally {
                _uiState.update {
                    it.copy(isLoading = false)
                }
            }
        }
    }

    fun getSongsFromAlbum(id: Int) {
        viewModelScope.launch {
            songRepository.getSongsFromAlbum(id)
                .catch {
                    _uiState.update {
                        it.copy(errorMessage = it.errorMessage)
                    }
                }.collectLatest { songs ->
                    _uiState.update {
                        it.copy(songs = songs.map { entity ->
                            entity.toSong()
                        })
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

data class AlbumUiState(
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false,
    val errorMessage: String? = null,
    val albums: List<Album> = emptyList(),
    val songs: List<Song> = emptyList()
)
