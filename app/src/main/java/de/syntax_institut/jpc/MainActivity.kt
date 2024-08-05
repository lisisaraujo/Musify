package de.syntax_institut.jpc

import SongDetailCardView
import SongProgressBar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.syntax_institut.jpc.models.Song
import de.syntax_institut.jpc.ui.components.PlaybackControls
import de.syntax_institut.jpc.ui.components.SongTopAppBar
import de.syntax_institut.jpc.ui.theme.JPCTheme

class MainActivity : ComponentActivity() {

    private val song = Song(R.drawable.care_4_u_album_cover, "Try Again", "Aaliyah")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JPCTheme(darkTheme = true) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        SongTopAppBar(artistName = song.artist)
                        SongDetailCardView(song)
                        Spacer(modifier = Modifier.height(16.dp))
                        SongProgressBar(
                            currentPosition = 3.3.toFloat(),
                            duration = 120,
                            onPositionChange = { newPosition ->
                            }
                        )
                        PlaybackControls()
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun MainContentPreview() {
        JPCTheme(darkTheme = true) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Column {
                    SongTopAppBar(artistName = song.artist)
                    SongDetailCardView(song)
                    Spacer(modifier = Modifier.height(16.dp))
                    SongProgressBar(
                        currentPosition = 3.3.toFloat(),
                        duration = 120,
                        onPositionChange = { newPosition ->
                        }
                    )
                    PlaybackControls()
                }
            }
        }
    }
}







