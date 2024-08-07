package de.syntax_institut.jpc.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import de.syntax_institut.jpc.data.models.Playlist
import de.syntax_institut.jpc.ui.ViewModels.PlaylistsViewModel

@Composable
fun LazyColumnPlaylists(viewModel: PlaylistsViewModel = viewModel()) {
    val playlists by viewModel.playlists.collectAsState()
    val selectedPlaylistIds by viewModel.selectedPlaylistIds.collectAsState()

    LazyColumn {
        items(playlists, key = { it.playlistID }) { playlist ->
            PlaylistItem(
                playlist = playlist,
                isSelected = selectedPlaylistIds.contains(playlist.playlistID),
                onPlaylistSelected = { viewModel.togglePlaylistSelection(it.playlistID) }
            )
        }
    }
}





