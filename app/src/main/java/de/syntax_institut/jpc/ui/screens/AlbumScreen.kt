import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.syntax_institut.jpc.R
import de.syntax_institut.jpc.data.models.Album
import de.syntax_institut.jpc.data.models.Song
import de.syntax_institut.jpc.ui.ViewModels.SongPlayViewModel
import de.syntax_institut.jpc.ui.components.SongListItem

@Composable
fun AlbumScreen(modifier: Modifier = Modifier, onBackClick: () -> Unit, onSongSelected: (Song) -> Unit) {
    var allSongs by remember { mutableStateOf(emptyList<Song>()) }
    val viewModel: SongPlayViewModel = viewModel()

    val album = Album(
        albumID = 1,
        title = "I care 4 U",
        artistName = "Aaliyah",
        songs = allSongs,
        releaseYear = 2002
    )

    LaunchedEffect(Unit) {
        allSongs = viewModel.loadSongs()
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Box(modifier = modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.care_4_u_album_cover),
                    contentDescription = "Album Cover",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.TopStart)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    Text(
                        text = album.title,
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = album.artistName,
                        color = Color.White,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "Album - ${album.releaseYear}",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }
        }

        items(allSongs, key = { it.id }) { song ->
            SongListItem(song, onSongSelected = { onSongSelected(song) })
        }
    }
}

