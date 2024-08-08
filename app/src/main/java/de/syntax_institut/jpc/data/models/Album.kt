package de.syntax_institut.jpc.data.models

data class Album (
    val albumID: Int,
    val songs: List<Song>,
    val title: String,
    val artistName: String,
    val releaseYear: Int
)