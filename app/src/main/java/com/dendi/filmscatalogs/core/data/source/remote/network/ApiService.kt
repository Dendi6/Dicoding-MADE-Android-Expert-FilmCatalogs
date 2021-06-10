package com.dendi.filmscatalogs.core.data.source.remote.network

import com.dendi.filmscatalogs.BuildConfig
import com.dendi.filmscatalogs.core.data.source.remote.response.ResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("trending/movie/week")
    fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.TOKEN
    ): Call<ResponseItem>

    @GET("trending/tv/week")
    fun getTv(
        @Query("api_key") apiKey: String = BuildConfig.TOKEN
    ): Call<ResponseItem>
}