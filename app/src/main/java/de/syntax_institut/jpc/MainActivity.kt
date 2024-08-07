package de.syntax_institut.jpc

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
                        startDestination = PlayingSongScreen
                    ) {


                        composable<PlayingSongScreen> {
                            PlayingSongScreen(
                                onMenu = { TODO() },
                                onClose = { TODO() },
                                onAddToPlaylist = {navController.navigate(AddToPlaylistScreen)}
                            )
                        }
                        composable<AddToPlaylistScreen> {
                            AddToPlaylistScreen(
                                onClose = { navController.navigateUp() },
                                onNewPlaylist = { TODO() }
                            )
                        }
                    }
                }
            }
        }
    }

    @Serializable
    data object PlayingSongScreen

    @Serializable
    data object AddToPlaylistScreen

}









