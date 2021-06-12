package com.dendi.filmscatalogs.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dendi.filmcatalogs.core.domain.model.Film
import com.dendi.filmcatalogs.core.domain.usecase.FilmUseCase

class FavoriteViewModel(private val filmUseCase: FilmUseCase) : ViewModel() {

    fun getFavorite(): LiveData<List<Film>> = filmUseCase.getFavorited().asLiveData()
}