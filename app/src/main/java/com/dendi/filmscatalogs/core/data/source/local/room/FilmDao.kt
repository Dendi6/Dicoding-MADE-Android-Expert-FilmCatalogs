package com.dendi.filmscatalogs.core.data.source.local.room

import androidx.room.*
import com.dendi.filmscatalogs.core.data.source.local.entity.ListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {
    @Query("SELECT * FROM listentities WHERE type = 'movie'")
    fun getMovies(): Flow<List<ListEntity>>

    @Query("SELECT * FROM listentities WHERE type = 'tv'")
    fun getTvShow(): Flow<List<ListEntity>>

    @Query("SELECT * FROM listentities WHERE favorited = 1")
    fun getFavorite(): Flow<List<ListEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(films: List<ListEntity>)

    @Update
    fun updateFilm(films: ListEntity)
}