package com.dendi.filmscatalogs.core.utils

import com.dendi.filmscatalogs.core.data.source.local.entity.ListEntity
import com.dendi.filmscatalogs.core.data.source.remote.response.ListResponse

object DataMapper {
    fun mapResponsesToEntities(input: List<ListResponse>): List<ListEntity> {
        val tourismList = ArrayList<ListEntity>()
        input.map {
            val films = ListEntity(
                id = it.id,
                title = it.title,
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
}