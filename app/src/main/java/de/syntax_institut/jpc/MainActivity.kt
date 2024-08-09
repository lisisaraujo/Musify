package de.syntax_institut.jpc

import AlbumScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
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

        // Enable edge-to-edge content
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberNavController()

            JPCTheme(darkTheme = true) {

                val isDarkMode = isSystemInDarkTheme()
                val statusBarColor = if (isDarkMode) Color.White else Color.Black
                val navigationBarColor = if (isDarkMode) Color.White else Color.Black

                // Set the system bar colors
                SideEffect {
                    window.statusBarColor = statusBarColor.toArgb()
                    window.navigationBarColor = navigationBarColor.toArgb()
                }

                Scaffold { contentPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = AlbumScreenRoute
                    ) {
                        composable<PlayingSongScreenRoute> { backStackEntry ->
                            val route = backStackEntry.toRoute<PlayingSongScreenRoute>()
                            PlayingSongScreen(
//                                modifier = Modifier.padding(contentPadding).fillMaxSize(),
                                onMenu = { /* TODO */ },
                                onClose = { navController.navigate(AlbumScreenRoute) },
                                onAddToPlaylist = { navController.navigate(AddToPlaylistScreenRoute) },
                                songID = route.songID
                            )
                        }
                        composable<AddToPlaylistScreenRoute> {
                            AddToPlaylistScreen(
//                                modifier = Modifier.padding(contentPadding).fillMaxSize(),
                                onClose = { navController.navigateUp() },
                                onNewPlaylist = { /* TODO */ }
                            )
                        }
                        composable<AlbumScreenRoute> {
                            AlbumScreen(
                                modifier = Modifier.padding(contentPadding).fillMaxSize(),
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
