package de.syntax_institut.jpc

import AlbumScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.syntax_institut.jpc.ui.screens.AddToPlaylistScreen
import de.syntax_institut.jpc.ui.screens.PlayingSongScreen
import de.syntax_institut.jpc.ui.theme.JPCTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            JPCTheme(darkTheme = true) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = PlayingSongScreenRoute
                    ) {

                        composable<PlayingSongScreenRoute> {
                            PlayingSongScreen(
                                onMenu = { TODO() },
                                onClose = { navController.navigate(AlbumScreenRoute) },
                                onAddToPlaylist = { navController.navigate(AddToPlaylistScreenRoute) }
                            )
                        }
                        composable<AddToPlaylistScreenRoute> {
                            AddToPlaylistScreen(
                                onClose = { navController.navigateUp() },
                                onNewPlaylist = { TODO() }
                            )
                        }

                        composable<AlbumScreenRoute> {
                            AlbumScreen(onBackClick = { navController.navigateUp() }, onSongSelected = {navController.navigate(PlayingSongScreenRoute) })
                        }
                    }
                }
            }
        }
    }

    @Serializable
    data object PlayingSongScreenRoute

    @Serializable
    data object AddToPlaylistScreenRoute

    @Serializable
    data object AlbumScreenRoute

}









