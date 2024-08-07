package de.syntax_institut.jpc.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.syntax_institut.jpc.data.models.Playlist
import de.syntax_institut.jpc.data.models.Song


@Database(entities = [Song::class, Playlist::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val dao: AppDao
}

private lateinit var INSTANCE: AppDatabase

fun getInstance(context: Context): AppDatabase {
    synchronized(AppDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context = context,
                klass = AppDatabase::class.java,
                name = "app_database"
            ).build()
        }
        return INSTANCE
    }
}