package com.dendi.filmscatalogs.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailResponse(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("backdrop_path")
    val backdropPath: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("overview")
    val overview: String,
)
