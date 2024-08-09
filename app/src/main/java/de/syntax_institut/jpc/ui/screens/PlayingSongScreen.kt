package de.syntax_institut.jpc.ui.screens

import SongDetailCardView
import SongProgressBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.syntax_institut.jpc.R
import de.syntax_institut.jpc.data.models.Song
import de.syntax_institut.jpc.ui.ViewModels.SongsViewModel
import de.syntax_institut.jpc.ui.components.PlaybackControls
import de.syntax_institut.jpc.ui.components.TopAppBar
import kotlinx.coroutines.delay

@Composable
fun PlayingSongScreen(
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
    onMenu: () -> Unit,
    onAddToPlaylist: () -> Unit,
    songID: Int
) {
    val viewModel: SongsViewModel = viewModel()
    var allSongs by remember { mutableStateOf(emptyList<Song>()) }
    var currentPosition by remember { mutableStateOf(0) }
    var isPlaying by remember { mutableStateOf(false) }

    var currentSong by remember { mutableStateOf<Song?>(null) }
    var currentSongIndex by remember { mutableStateOf(-1) }

    LaunchedEffect(Unit) {
        allSongs = viewModel.loadSongs()
        currentSongIndex = allSongs.indexOfFirst { it.id == songID }
        currentSong = allSongs.getOrNull(currentSongIndex)
    }

    LaunchedEffect(currentSongIndex) {
        currentSong = allSongs.getOrNull(currentSongIndex)
        currentPosition = 0 // Reset position when song changes
    }

    val songToDisplay = currentSong ?: Song(
        albumCover = R.drawable.care_4_u_album_cover,
        title = "No Song",
        artist = "Unknown",
        duration = 0
    )

    LaunchedEffect(isPlaying, currentPosition) {
        if (isPlaying) {
            while (currentPosition < songToDisplay.duration) {
                delay(1000)
                currentPosition += 1
            }
            if (currentPosition >= songToDisplay.duration) {
                isPlaying = false
                currentPosition = 0
            }
        }
    }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            TopAppBar(
                title = songToDisplay.artist,
                navigationIcon = Icons.Filled.KeyboardArrowDown,
                navigationIconContentDescription = "",
                actionIcon = Icons.Filled.MoreVert,
                actionIconContentDescription = "",
                onNavigationClick = onClose,
                onActionClick = onMenu
            )
            SongDetailCardView(songToDisplay, onAddToPlaylist = onAddToPlaylist)
            Spacer(modifier = Modifier.height(16.dp))
            SongProgressBar(
                currentPosition = currentPosition.toFloat(),
                duration = songToDisplay.duration,
                onPositionChange = { newPosition ->
                    currentPosition = newPosition.toInt()
                }
            )
            PlaybackControls(
                onPreviousClick = {
                    currentSongIndex =
                        viewModel.getPreviousSongIndex(currentSongIndex, allSongs.size)
                    currentPosition = 0
                },
                onPlayClick = { isPlaying = !isPlaying },
                onNextClick = {
                    currentSongIndex = viewModel.getNextSongIndex(currentSongIndex, allSongs.size)
                    currentPosition = 0
                },
                isPlaying = isPlaying
            )
        }
    }
}

