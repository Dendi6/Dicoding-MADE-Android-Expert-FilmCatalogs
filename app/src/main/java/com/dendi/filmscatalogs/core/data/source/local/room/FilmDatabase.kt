package com.dendi.filmscatalogs.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dendi.filmscatalogs.core.data.source.local.entity.ListEntity

@Database(
    entities = [ListEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FilmDatabase : RoomDatabase() {

    abstract fun filmDao(): FilmDao

}