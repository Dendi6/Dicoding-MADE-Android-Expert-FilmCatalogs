package com.dendi.filmscatalogs.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dendi.filmcatalogs.core.domain.model.Film
import com.dendi.filmcatalogs.core.domain.usecase.FilmUseCase
import com.dendi.filmcatalogs.core.vo.Resource

class HomeViewModel(private val filmUseCase: FilmUseCase) : ViewModel() {

    fun getMovies(): LiveData<Resource<List<Film>>> = filmUseCase.getAllMovies().asLiveData()

    fun getTvShow(): LiveData<Resource<List<Film>>> = filmUseCase.getAllTvShow().asLiveData()
}