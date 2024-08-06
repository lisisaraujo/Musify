package de.syntax_institut.jpc.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class Song(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val albumCover: Int,
    val title: String,
    val artist: String,
    val duration: Int
)

