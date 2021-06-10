package com.dendi.filmscatalogs.core.domain.usecase

import androidx.lifecycle.LiveData
import com.dendi.filmscatalogs.core.domain.model.Film
import com.dendi.filmscatalogs.core.domain.repository.IFilmRepository
import com.dendi.filmscatalogs.core.vo.Resource

class FilmInteractor(private val iFilmRepository: IFilmRepository) : FilmUseCase {

    override fun getAllMovies(): LiveData<Resource<List<Film>>> = iFilmRepository.getAllMovies()

    override fun getAllTvShow(): LiveData<Resource<List<Film>>> = iFilmRepository.getAllTvShow()

    override fun getFavorited(): LiveData<List<Film>> = iFilmRepository.getFavorited()

    override fun setFilmFavorite(film: Film, state: Boolean) =
        iFilmRepository.setFilmFavorite(film, state)
}