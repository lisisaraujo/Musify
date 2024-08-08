package de.syntax_institut.jpc.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import de.syntax_institut.jpc.data.models.Playlist
import de.syntax_institut.jpc.data.models.Song

@Composable
fun SongListItem(song: Song,  onSongSelected: (Song) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSongSelected(song) }
            .padding(16.dp)

    ) {
        Text(text = song.title, style = MaterialTheme.typography.bodyLarge)
        Text(text = song.artist, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
    }
}