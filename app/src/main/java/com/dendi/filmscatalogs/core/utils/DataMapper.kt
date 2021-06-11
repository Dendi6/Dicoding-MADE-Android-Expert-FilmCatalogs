package com.dendi.filmscatalogs.core.utils

import com.dendi.filmscatalogs.core.data.source.local.entity.ListEntity
import com.dendi.filmscatalogs.core.data.source.remote.response.ListResponse
import com.dendi.filmscatalogs.core.domain.model.Film

object DataMapper {
    fun mapResponsesToEntities(input: List<ListResponse>): List<ListEntity> {
        val tourismList = ArrayList<ListEntity>()
        input.map {
            val films = ListEntity(
                id = it.id,
                title = it.title,
                name = it.name,
                images = it.posterPath,
                poster = it.backdropPath,
                overview = it.overview,
                favorited = false,
                type = it.mediaType
            )
            tourismList.add(films)
        }
        return tourismList
    }

    fun mapEntitiesToDomain(input: List<ListEntity>): List<Film> =
        input.map {
            Film(
                id = it.id,
                title = it.title,
                name = it.name,
                images = it.images,
                poster = it.poster,
                overview = it.overview,
                favorited = it.favorited,
                type = it.type
            )
        }

    fun mapDomainToEntity(input: Film) = ListEntity(
        id = input.id,
        title = input.title,
        name = input.name,
        images = input.images,
        poster = input.poster,
        overview = input.overview,
        favorited = input.favorited,
        type = input.type
    )
}