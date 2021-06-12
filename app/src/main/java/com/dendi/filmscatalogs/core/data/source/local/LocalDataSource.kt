package com.dendi.filmscatalogs.core.data.source.local

import com.dendi.filmscatalogs.core.data.source.local.entity.ListEntity
import com.dendi.filmscatalogs.core.data.source.local.room.FilmDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mFilmDao: FilmDao) {

    fun getMovies(): Flow<List<ListEntity>> = mFilmDao.getMovies()

    fun getTvShow(): Flow<List<ListEntity>> = mFilmDao.getTvShow()

    fun getFavorited(): Flow<List<ListEntity>> = mFilmDao.getFavorite()

    suspend fun insertFilm(film: List<ListEntity>) = mFilmDao.insertFilm(film)

    fun setFilmFavorite(film: ListEntity, newState: Boolean) {
        film.favorited = newState
        mFilmDao.updateFilm(film)
    }
}