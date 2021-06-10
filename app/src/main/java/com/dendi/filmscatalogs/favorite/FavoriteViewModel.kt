package com.dendi.filmscatalogs.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dendi.filmscatalogs.core.domain.model.Film
import com.dendi.filmscatalogs.core.domain.usecase.FilmUseCase

class FavoriteViewModel(private val filmUseCase: FilmUseCase) : ViewModel() {

    fun getFavorite(): LiveData<List<Film>> = filmUseCase.getFavorited()
}