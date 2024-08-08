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
import de.syntax_institut.jpc.ui.ViewModels.SongPlayViewModel
import de.syntax_institut.jpc.ui.components.PlaybackControls
import de.syntax_institut.jpc.ui.components.TopAppBar
import kotlinx.coroutines.delay

@Composable
fun PlayingSongScreen(
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
    onMenu: () -> Unit,
    onAddToPlaylist: () -> Unit,
) {
    var allSongs by remember { mutableStateOf(emptyList<Song>()) }
    var currentSongIndex by remember { mutableStateOf(0) }
    var currentPosition by remember { mutableStateOf(0) }
    var isPlaying by remember { mutableStateOf(false) }

    val viewModel: SongPlayViewModel = viewModel()

    LaunchedEffect(Unit) {
        allSongs = viewModel.loadSongs()
    }

    val currentSong = allSongs.getOrNull(currentSongIndex) ?: Song(
        albumCover = R.drawable.care_4_u_album_cover,
        title = "No Song",
        artist = "Unknown",
        duration = 0
    )

    LaunchedEffect(isPlaying, currentSong) {
        while (isPlaying && currentPosition < currentSong.duration) {
            delay(1000)
            currentPosition =
                viewModel.updatePosition(currentPosition, isPlaying, currentSong.duration)
        }
        if (currentPosition >= currentSong.duration) {
            isPlaying = false
            currentPosition = 0
        }
    }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            TopAppBar(
                title = currentSong.artist,
                navigationIcon = Icons.Filled.KeyboardArrowDown,
                navigationIconContentDescription = "",
                actionIcon = Icons.Filled.MoreVert,
                actionIconContentDescription = "",
                onNavigationClick = onClose,
                onActionClick = onMenu
            )
            SongDetailCardView(currentSong, onAddToPlaylist = onAddToPlaylist)
            Spacer(modifier = Modifier.height(16.dp))
            SongProgressBar(
                currentPosition = currentPosition.toFloat(),
                duration = currentSong.duration,
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
