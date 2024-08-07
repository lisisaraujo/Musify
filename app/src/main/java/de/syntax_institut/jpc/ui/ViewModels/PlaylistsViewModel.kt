package de.syntax_institut.jpc.ui.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import de.syntax_institut.jpc.data.Repository
import de.syntax_institut.jpc.data.models.Playlist
import de.syntax_institut.jpc.data.models.Song
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlaylistsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository(application)

    private val _playlists = MutableStateFlow<List<Playlist>>(emptyList())
    val playlists: StateFlow<List<Playlist>> = _playlists

    private val _selectedPlaylistIds = MutableStateFlow<Set<Int>>(emptySet())
    val selectedPlaylistIds: StateFlow<Set<Int>> = _selectedPlaylistIds

    init {
        viewModelScope.launch {
            loadPlaylists()
        }
    }

    private suspend fun loadPlaylists() {
        _playlists.value = repository.getAllPlaylists()
    }

    fun togglePlaylistSelection(playlistId: Int) {
        val currentSelectedIds = _selectedPlaylistIds.value.toMutableSet()
        val updatedPlaylists = _playlists.value.map { playlist ->
            if (playlist.playlistID == playlistId) {
                if (currentSelectedIds.contains(playlistId)) {
                    currentSelectedIds.remove(playlistId)
                    playlist.copy(numOfSongs = playlist.numOfSongs - 1)
                } else {
                    currentSelectedIds.add(playlistId)
                    playlist.copy(numOfSongs = playlist.numOfSongs + 1)
                }
            } else {
                playlist
            }
        }
        _selectedPlaylistIds.value = currentSelectedIds
        _playlists.value = updatedPlaylists
    }

}
