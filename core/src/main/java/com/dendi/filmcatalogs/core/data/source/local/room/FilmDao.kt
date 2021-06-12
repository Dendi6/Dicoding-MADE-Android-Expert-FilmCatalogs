package com.dendi.filmcatalogs.core.data.source.local.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {
    @Query("SELECT * FROM listentities WHERE type = 'movie'")
    fun getMovies(): Flow<List<com.dendi.filmcatalogs.core.data.source.local.entity.ListEntity>>

    @Query("SELECT * FROM listentities WHERE type = 'tv'")
    fun getTvShow(): Flow<List<com.dendi.filmcatalogs.core.data.source.local.entity.ListEntity>>

    @Query("SELECT * FROM listentities WHERE favorited = 1")
    fun getFavorite(): Flow<List<com.dendi.filmcatalogs.core.data.source.local.entity.ListEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(films: List<com.dendi.filmcatalogs.core.data.source.local.entity.ListEntity>)

    @Update
    fun updateFilm(films: com.dendi.filmcatalogs.core.data.source.local.entity.ListEntity)
}