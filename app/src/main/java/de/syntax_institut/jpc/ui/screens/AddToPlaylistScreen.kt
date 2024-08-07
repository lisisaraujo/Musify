package de.syntax_institut.jpc.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
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
import androidx.lifecycle.viewmodel.compose.viewModel
import de.syntax_institut.jpc.data.models.Playlist
import de.syntax_institut.jpc.data.models.Song
import de.syntax_institut.jpc.ui.ViewModels.PlaylistsViewModel
import de.syntax_institut.jpc.ui.components.LazyColumnPlaylists
import de.syntax_institut.jpc.ui.components.TopAppBar

@Composable
fun AddToPlaylistScreen(
    onClose: () -> Unit,
    onNewPlaylist: () -> Unit,
    viewModel: PlaylistsViewModel = viewModel()
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            TopAppBar(
                title = "Playlists",
                navigationIcon = Icons.Filled.Close,
                navigationIconContentDescription = "Close",
                actionIcon = Icons.Filled.Add,
                actionIconContentDescription = "New Playlist",
                onNavigationClick = onClose,
                onActionClick = onNewPlaylist
            )
            LazyColumnPlaylists(viewModel)
        }
    }
}

