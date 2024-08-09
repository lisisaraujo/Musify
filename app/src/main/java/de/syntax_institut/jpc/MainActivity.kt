package de.syntax_institut.jpc

import AlbumScreen
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import de.syntax_institut.jpc.ui.screens.AddToPlaylistScreen
import de.syntax_institut.jpc.ui.screens.PlayingSongScreen
import de.syntax_institut.jpc.ui.theme.JPCTheme
import kotlinx.serialization.Serializable


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        setContent {
            val navController = rememberNavController()

            JPCTheme(darkTheme = true) {
                Scaffold(
                    containerColor = MaterialTheme.colorScheme.background
                ) { paddingValues ->
                    paddingValues.calculateTopPadding()

                    NavHost(
                        navController = navController,
                        startDestination = AlbumScreenRoute
                    ) {
                        composable<PlayingSongScreenRoute> { backStackEntry ->
                            val route = backStackEntry.toRoute<PlayingSongScreenRoute>()
                            PlayingSongScreen(
                                onMenu = { /* TODO */ },
                                onClose = { navController.navigate(AlbumScreenRoute) },
                                onAddToPlaylist = { navController.navigate(AddToPlaylistScreenRoute) },
                                songID = route.songID
                            )
                        }
                        composable<AddToPlaylistScreenRoute> {
                            AddToPlaylistScreen(
                                onClose = { navController.navigateUp() },
                                onNewPlaylist = { /* TODO */ }
                            )
                        }
                        composable<AlbumScreenRoute> {
                            AlbumScreen(
                                onBackClick = { navController.navigateUp() },
                                onSongSelected = { song ->
                                    navController.navigate(PlayingSongScreenRoute(song.id))
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    @Serializable
    data class PlayingSongScreenRoute(val songID: Int)

    @Serializable
    data object AddToPlaylistScreenRoute

    @Serializable
    data object AlbumScreenRoute
}










