package de.syntax_institut.jpc.ui.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import de.syntax_institut.jpc.data.Repository
import de.syntax_institut.jpc.data.models.Song

val TAG = "SongPlayViewModel"
class SongsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository(application)

//    private val _songs = MutableStateFlow<List<Song>>(emptyList())
//    val songs: StateFlow<List<Song>> = _songs
    suspend fun loadSongs(): List<Song>{
        return repository.getAllSongs()
    }

    fun getNextSongIndex(currentIndex: Int, totalSongs: Int): Int {
        return if (currentIndex < totalSongs - 1) currentIndex + 1 else 0
    }

    fun getPreviousSongIndex(currentIndex: Int, totalSongs: Int): Int {
        return if (currentIndex > 0) currentIndex - 1 else totalSongs - 1
    }

    fun updatePosition(currentPosition: Int, isPlaying: Boolean, songDuration: Int): Int {
        return if (isPlaying && currentPosition < songDuration) {
            currentPosition + 1
        } else {
            currentPosition
        }
    }

}
