package de.syntax_institut.jpc.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.syntax_institut.jpc.data.models.Song


@Database(entities = [Song::class], version = 1)
abstract class SongDatabase : RoomDatabase() {
    abstract val dao: SongDao
}

private lateinit var INSTANCE: SongDatabase

fun getInstance(context: Context): SongDatabase {
    synchronized(SongDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context = context,
                klass = SongDatabase::class.java,
                name = "song_database"
            ).build()
        }
        return INSTANCE
    }
}