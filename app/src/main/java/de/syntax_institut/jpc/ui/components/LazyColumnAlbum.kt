package de.syntax_institut.jpc.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.syntax_institut.jpc.data.models.Song

@Composable
fun LazyColumnAlbum(modifier: Modifier = Modifier, album: List<Song>) {

    LazyColumn {
        items(album, key = { it.id }) { song ->
            Song(
                id = song.id,
                albumCover = song.albumCover,
                artist = song.artist,
                title = song.title,
                duration = song.duration,
            )
        }
    }
}