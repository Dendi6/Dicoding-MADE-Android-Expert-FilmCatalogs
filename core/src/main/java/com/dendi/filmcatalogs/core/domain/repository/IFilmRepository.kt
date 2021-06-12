package com.dendi.filmcatalogs.core.domain.repository

import com.dendi.filmcatalogs.core.domain.model.Film
import com.dendi.filmcatalogs.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface IFilmRepository {

    fun getAllMovies(): Flow<Resource<List<Film>>>

    fun getAllTvShow(): Flow<Resource<List<Film>>>

    fun getFavorited(): Flow<List<Film>>

    fun setFilmFavorite(film: Film, state: Boolean)

}