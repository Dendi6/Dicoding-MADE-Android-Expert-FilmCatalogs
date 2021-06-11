package com.dendi.filmscatalogs.core.domain.usecase

import com.dendi.filmscatalogs.core.domain.model.Film
import com.dendi.filmscatalogs.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface FilmUseCase {
    fun getAllMovies(): Flow<Resource<List<Film>>>

    fun getAllTvShow(): Flow<Resource<List<Film>>>

    fun getFavorited(): Flow<List<Film>>

    fun setFilmFavorite(film: Film, state: Boolean)
}