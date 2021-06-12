package com.dendi.filmcatalogs.core.data.source.remote.network

import com.dendi.filmcatalogs.core.BuildConfig
import com.dendi.filmcatalogs.core.data.source.remote.response.ResponseItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("trending/movie/week")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.TOKEN
    ): ResponseItem

    @GET("trending/tv/week")
    suspend fun getTv(
        @Query("api_key") apiKey: String = BuildConfig.TOKEN
    ): ResponseItem
}