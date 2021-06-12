package com.dendi.filmcatalogs.core.data.source.local

import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mFilmDao: com.dendi.filmcatalogs.core.data.source.local.room.FilmDao) {

    fun getMovies(): Flow<List<com.dendi.filmcatalogs.core.data.source.local.entity.ListEntity>> =
        mFilmDao.getMovies()

    fun getTvShow(): Flow<List<com.dendi.filmcatalogs.core.data.source.local.entity.ListEntity>> =
        mFilmDao.getTvShow()

    fun getFavorited(): Flow<List<com.dendi.filmcatalogs.core.data.source.local.entity.ListEntity>> =
        mFilmDao.getFavorite()

    suspend fun insertFilm(film: List<com.dendi.filmcatalogs.core.data.source.local.entity.ListEntity>) =
        mFilmDao.insertFilm(film)

    fun setFilmFavorite(
        film: com.dendi.filmcatalogs.core.data.source.local.entity.ListEntity,
        newState: Boolean
    ) {
        film.favorited = newState
        mFilmDao.updateFilm(film)
    }
}