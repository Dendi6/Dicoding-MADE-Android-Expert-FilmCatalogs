package com.dendi.filmcatalogs.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dendi.filmcatalogs.core.data.source.local.entity.ListEntity

@Database(
    entities = [com.dendi.filmcatalogs.core.data.source.local.entity.ListEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FilmDatabase : RoomDatabase() {

    abstract fun filmDao(): com.dendi.filmcatalogs.core.data.source.local.room.FilmDao

}