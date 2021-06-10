package com.dendi.filmscatalogs.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dendi.filmscatalogs.core.data.FilmRepository
import com.dendi.filmscatalogs.core.domain.model.Film
import com.dendi.filmscatalogs.core.domain.usecase.FilmUseCase
import com.dendi.filmscatalogs.core.vo.Resource

class HomeViewModel(private val filmUseCase: FilmUseCase) : ViewModel() {

    fun getMovies(): LiveData<Resource<List<Film>>> = filmUseCase.getAllMovies()

    fun getTvShow(): LiveData<Resource<List<Film>>> = filmUseCase.getAllTvShow()
}