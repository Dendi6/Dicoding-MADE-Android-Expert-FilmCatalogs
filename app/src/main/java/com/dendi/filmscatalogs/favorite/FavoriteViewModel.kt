package com.dendi.filmscatalogs.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dendi.filmscatalogs.core.data.FilmRepository
import com.dendi.filmscatalogs.core.data.source.local.entity.ListEntity

class FavoriteViewModel(private val filmRepository: FilmRepository) : ViewModel() {

    fun getFavorite(): LiveData<List<ListEntity>> = filmRepository.getFavorited()

    fun setFavorited(filmsEntity: ListEntity) {
        val newState = !filmsEntity.favorited
        filmRepository.setFilmFavorite(filmsEntity, newState)
    }
}