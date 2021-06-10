package com.dendi.filmscatalogs.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dendi.filmscatalogs.core.data.FilmRepository
import com.dendi.filmscatalogs.core.data.source.local.entity.ListEntity
import com.dendi.filmscatalogs.core.vo.Resource

class HomeViewModel(private val filmRepository: FilmRepository) : ViewModel() {

    fun getMovies(): LiveData<Resource<List<ListEntity>>> = filmRepository.getAllMovies()

    fun getTvShow(): LiveData<Resource<List<ListEntity>>> = filmRepository.getAllTvShow()
}