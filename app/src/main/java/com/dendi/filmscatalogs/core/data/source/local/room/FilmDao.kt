package com.dendi.filmscatalogs.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dendi.filmscatalogs.core.data.source.local.entity.ListEntity

@Dao
interface FilmDao {
    @Query("SELECT * FROM listentities WHERE type = 'movie'")
    fun getMovies(): LiveData<List<ListEntity>>

    @Query("SELECT * FROM listentities WHERE type = 'tv'")
    fun getTvShow(): LiveData<List<ListEntity>>

    @Query("SELECT * FROM listentities WHERE favorited = 1")
    fun getFavorite(): LiveData<List<ListEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilm(films: List<ListEntity>)

    @Update
    fun updateFilm(films: ListEntity)
}