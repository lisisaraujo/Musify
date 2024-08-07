package de.syntax_institut.jpc.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "playlists")
data class Playlist(
    @PrimaryKey(autoGenerate = true) val playlistID: Int = 0,
    val coverImage: Int,
    val playlistName: String,
    var numOfSongs: Int,
)