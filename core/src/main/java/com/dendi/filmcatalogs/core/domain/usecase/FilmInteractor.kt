package com.dendi.filmcatalogs.core.domain.usecase

import com.dendi.filmcatalogs.core.domain.model.Film
import com.dendi.filmcatalogs.core.domain.repository.IFilmRepository

class FilmInteractor(private val iFilmRepository: IFilmRepository) : FilmUseCase {

    override fun getAllMovies() = iFilmRepository.getAllMovies()

    override fun getAllTvShow() = iFilmRepository.getAllTvShow()

    override fun getFavorited() = iFilmRepository.getFavorited()

    override fun setFilmFavorite(film: Film, state: Boolean) =
        iFilmRepository.setFilmFavorite(film, state)
}