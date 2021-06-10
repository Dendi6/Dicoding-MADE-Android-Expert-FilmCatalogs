package com.dendi.filmscatalogs.core.domain.repository

import androidx.lifecycle.LiveData
import com.dendi.filmscatalogs.core.domain.model.Film
import com.dendi.filmscatalogs.core.vo.Resource

interface IFilmRepository {

    fun getAllMovies(): LiveData<Resource<List<Film>>>

    fun getAllTvShow(): LiveData<Resource<List<Film>>>

    fun getFavorited(): LiveData<List<Film>>

    fun setFilmFavorite(film: Film, state: Boolean)

}