package de.syntax_institut.jpc.data

import android.content.Context
import de.syntax_institut.jpc.R
import de.syntax_institut.jpc.data.local.AppDao
import de.syntax_institut.jpc.data.local.getInstance
import de.syntax_institut.jpc.data.models.Playlist
import de.syntax_institut.jpc.data.models.Song
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(context: Context) {

    private val appDB = getInstance(context)
    private val appDao: AppDao = appDB.dao


    private suspend fun isDatabaseEmpty(): Boolean {
        return appDao.getSongCount() == 0
    }


    private suspend fun populateDatabase() {
        val songs = listOf(
            Song(
                albumCover = R.drawable.care_4_u_album_cover,
                title = "Back and Forth",
                artist = "Aaliyah",
                duration = 229
            ),
            Song(
                albumCover = R.drawable.care_4_u_album_cover,
                title = "Are You That Somebody?",
                artist = "Aaliyah",
                duration = 265
            ),
            Song(
                albumCover = R.drawable.care_4_u_album_cover,
                title = "One In A Million",
                artist = "Aaliyah",
                duration = 269
            ),
            Song(
                albumCover = R.drawable.care_4_u_album_cover,
                title = "I Care 4 U",
                artist = "Aaliyah",
                duration = 273
            ),
            Song(
                albumCover = R.drawable.care_4_u_album_cover,
                title = "More Than A Woman",
                artist = "Aaliyah",
                duration = 250
            ),
            Song(
                albumCover = R.drawable.care_4_u_album_cover,
                title = "Don't Know What To Tell Ya",
                artist = "Aaliyah",
                duration = 250
            ),
            Song(
                albumCover = R.drawable.care_4_u_album_cover,
                title = "Try Again",
                artist = "Aaliyah",
                duration = 242
            ),
            Song(
                albumCover = R.drawable.care_4_u_album_cover,
                title = "All I Need",
                artist = "Aaliyah",
                duration = 258
            ),
            Song(
                albumCover = R.drawable.care_4_u_album_cover,
                title = "Miss You",
                artist = "Aaliyah",
                duration = 235
            ),
            Song(
                albumCover = R.drawable.care_4_u_album_cover,
                title = "Don't Worry",
                artist = "Aaliyah",
                duration = 255
            ),
            Song(
                albumCover = R.drawable.care_4_u_album_cover,
                title = "Come Over",
                artist = "Aaliyah",
                duration = 257
            ),
            Song(
                albumCover = R.drawable.care_4_u_album_cover,
                title = "Erica Kane",
                artist = "Aaliyah",
                duration = 209
            ),
            Song(
                albumCover = R.drawable.care_4_u_album_cover,
                title = "At Your Best (You Are Love)",
                artist = "Aaliyah",
                duration = 280
            ),
            Song(
                albumCover = R.drawable.care_4_u_album_cover,
                title = "Got To Give It Up (Remix)",
                artist = "Aaliyah",
                duration = 265
            ),
            Song(
                albumCover = R.drawable.care_4_u_album_cover,
                title = "We Need A Resolution",
                artist = "Aaliyah",
                duration = 240
            ),
            Song(
                albumCover = R.drawable.care_4_u_album_cover,
                title = "Rock The Boat",
                artist = "Aaliyah",
                duration = 274
            )
        )

        val playlists = listOf(
            Playlist(
                coverImage = R.drawable.playlist1,
                playlistName = "Chill Vibes",
                numOfSongs = 12
            ),
            Playlist(
                coverImage = R.drawable.playlist4,
                playlistName = "Workout Beats",
                numOfSongs = 7
            ),
            Playlist(
                coverImage = R.drawable.playlist2,
                playlistName = "Top Hits",
                numOfSongs = 18
            ),
            Playlist(
                coverImage = R.drawable.care_4_u_album_cover,
                playlistName = "Relaxing Instrumentals",
                numOfSongs = 36
            ),
            Playlist(
                coverImage = R.drawable.playlist4,
                playlistName = "Party Time",
                numOfSongs = 89
            ),
            Playlist(
                coverImage = R.drawable.playlist1,
                playlistName = "Morning Motivation",
                numOfSongs = 17
            ),
            Playlist(
                coverImage = R.drawable.playlist2,
                playlistName = "Evening Chill",
                numOfSongs = 33
            ),
            Playlist(
                coverImage = R.drawable.care_4_u_album_cover,
                playlistName = "Summer Hits",
                numOfSongs = 9
            ),
            Playlist(
                coverImage = R.drawable.playlist1,
                playlistName = "Rock Classics",
                numOfSongs = 21
            ),
            Playlist(
                coverImage = R.drawable.playlist4,
                playlistName = "Jazz Essentials",
                numOfSongs = 8
            ),
            Playlist(
                coverImage = R.drawable.playlist2,
                playlistName = "Chill Vibes",
                numOfSongs = 12
            ),
            Playlist(
                coverImage = R.drawable.care_4_u_album_cover,
                playlistName = "Workout Beats",
                numOfSongs = 7
            ),
            Playlist(
                coverImage = R.drawable.playlist4,
                playlistName = "Top Hits",
                numOfSongs = 18
            ),
            Playlist(
                coverImage = R.drawable.playlist2,
                playlistName = "Relaxing Instrumentals",
                numOfSongs = 36
            ),
            Playlist(
                coverImage = R.drawable.playlist1,
                playlistName = "Party Time",
                numOfSongs = 89
            ),
            Playlist(
                coverImage = R.drawable.playlist1,
                playlistName = "Chill Vibes",
                numOfSongs = 12
            ),
            Playlist(
                coverImage = R.drawable.playlist4,
                playlistName = "Workout Beats",
                numOfSongs = 7
            ),
            Playlist(
                coverImage = R.drawable.playlist2,
                playlistName = "Top Hits",
                numOfSongs = 18
            ),
            Playlist(
                coverImage = R.drawable.care_4_u_album_cover,
                playlistName = "Relaxing Instrumentals",
                numOfSongs = 36
            ),
            Playlist(
                coverImage = R.drawable.playlist4,
                playlistName = "Party Time",
                numOfSongs = 89
            ),
            Playlist(
                coverImage = R.drawable.playlist1,
                playlistName = "Morning Motivation",
                numOfSongs = 17
            ),
            Playlist(
                coverImage = R.drawable.playlist2,
                playlistName = "Evening Chill",
                numOfSongs = 33
            ),
            Playlist(
                coverImage = R.drawable.care_4_u_album_cover,
                playlistName = "Summer Hits",
                numOfSongs = 9
            ),
            Playlist(
                coverImage = R.drawable.playlist1,
                playlistName = "Rock Classics",
                numOfSongs = 21
            ),
            Playlist(
                coverImage = R.drawable.playlist4,
                playlistName = "Jazz Essentials",
                numOfSongs = 8
            ),
            Playlist(
                coverImage = R.drawable.playlist2,
                playlistName = "Chill Vibes",
                numOfSongs = 12
            ),
            Playlist(
                coverImage = R.drawable.care_4_u_album_cover,
                playlistName = "Workout Beats",
                numOfSongs = 7
            ),
            Playlist(
                coverImage = R.drawable.playlist4,
                playlistName = "Top Hits",
                numOfSongs = 18
            ),
            Playlist(
                coverImage = R.drawable.playlist2,
                playlistName = "Relaxing Instrumentals",
                numOfSongs = 36
            ),
            Playlist(
                coverImage = R.drawable.playlist1,
                playlistName = "Party Time",
                numOfSongs = 89
            ),
        )

        appDao.insertSongs(songs)
        appDao.insertPlaylists(playlists)

    }


    suspend fun getAllSongs(): List<Song> {
        return withContext(Dispatchers.IO) {
            if (isDatabaseEmpty()) {
                populateDatabase()
            }
            appDao.getAllSongs()
        }
    }

    suspend fun getAllPlaylists(): List<Playlist> {
        return withContext(Dispatchers.IO) {
            if (isDatabaseEmpty()) {
                populateDatabase()
            }
            appDao.getAllPlaylists()
        }
    }


}
