package com.dendi.filmscatalogs.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dendi.filmscatalogs.core.data.FilmRepository
import com.dendi.filmscatalogs.core.data.source.local.entity.ListEntity
import com.dendi.filmscatalogs.core.vo.Resource

class HomeViewModel(private val filmRepository: FilmRepository) : ViewModel() {

    fun getMovies(): LiveData<Resource<PagedList<ListEntity>>> = filmRepository.getAllMovies()

    fun getTvShow(): LiveData<Resource<PagedList<ListEntity>>> = filmRepository.getAllTvShow()
}