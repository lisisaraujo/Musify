package de.syntax_institut.jpc.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.syntax_institut.jpc.data.models.Song

@Dao
interface SongDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongs(songs: List<Song>)

    @Query("SELECT * FROM Songs")
    suspend fun getAllSongs(): List<Song>

    @Query("SELECT * FROM Songs WHERE id = :id")
    suspend fun getSongById(id: Int): Song?

    @Query("SELECT COUNT(*) FROM Songs")
    suspend fun getSongCount(): Int
}
