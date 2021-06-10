package com.dendi.filmscatalogs.detail

import androidx.lifecycle.ViewModel
import com.dendi.filmscatalogs.core.data.FilmRepository
import com.dendi.filmscatalogs.core.domain.model.Film
import com.dendi.filmscatalogs.core.domain.usecase.FilmUseCase

class DetailActivityViewModel(private val filmUseCase: FilmUseCase): ViewModel() {
    private var id: Int = 0

    fun setSelectedFilm(id: Int) {
        this.id = id
    }

    fun setFavorite(filmEntity: Film, newState: Boolean) =
        filmUseCase.setFilmFavorite(filmEntity, newState)
}