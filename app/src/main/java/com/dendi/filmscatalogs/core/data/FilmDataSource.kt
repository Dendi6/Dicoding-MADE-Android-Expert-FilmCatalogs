package com.dendi.filmscatalogs.core.data

import androidx.lifecycle.LiveData
import com.dendi.filmscatalogs.core.data.source.local.entity.ListEntity
import com.dendi.filmscatalogs.core.vo.Resource

interface FilmDataSource {

    fun getAllMovies(): LiveData<Resource<List<ListEntity>>>

    fun getAllTvShow(): LiveData<Resource<List<ListEntity>>>

    fun getFavorited(): LiveData<List<ListEntity>>

    fun setFilmFavorite(film: ListEntity, state: Boolean)
}