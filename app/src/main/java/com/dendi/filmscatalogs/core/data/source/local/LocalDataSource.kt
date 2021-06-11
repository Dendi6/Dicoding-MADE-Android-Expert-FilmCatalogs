package com.dendi.filmscatalogs.core.data.source.local

import com.dendi.filmscatalogs.core.data.source.local.entity.ListEntity
import com.dendi.filmscatalogs.core.data.source.local.room.FilmDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(private val mFilmDao: FilmDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(filmDao: FilmDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(filmDao).apply { INSTANCE = this }
    }

    fun getMovies(): Flow<List<ListEntity>> = mFilmDao.getMovies()

    fun getTvShow(): Flow<List<ListEntity>> = mFilmDao.getTvShow()

    fun getFavorited(): Flow<List<ListEntity>> = mFilmDao.getFavorite()

    suspend fun insertFilm(film: List<ListEntity>) = mFilmDao.insertFilm(film)

    fun setFilmFavorite(film: ListEntity, newState: Boolean) {
        film.favorited = newState
        mFilmDao.updateFilm(film)
    }
}