package com.dendi.filmscatalogs.core.data.source.remote

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dendi.filmscatalogs.core.data.source.remote.network.ApiConfig
import com.dendi.filmscatalogs.core.data.source.remote.response.ListResponse
import com.dendi.filmscatalogs.core.data.source.remote.response.ResponseItem
import com.dendi.filmscatalogs.core.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }

    fun getAllMovies(): LiveData<ApiResponse<List<ListResponse>>> {
        EspressoIdlingResource.increment()
        val resultFilm = MutableLiveData<ApiResponse<List<ListResponse>>>()

        val client = ApiConfig.getApiService().getMovies()
        client.enqueue(object : Callback<ResponseItem> {
            override fun onResponse(
                call: Call<ResponseItem>,
                response: Response<ResponseItem>
            ) {
                if (response.isSuccessful) {
                    resultFilm.value = response.body()?.let { ApiResponse.success(it.results) }
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<ResponseItem>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }
        })

        return resultFilm
    }

    fun getAllTvShow(): LiveData<ApiResponse<List<ListResponse>>> {
        EspressoIdlingResource.increment()
        val resultFilm = MutableLiveData<ApiResponse<List<ListResponse>>>()

        val client = ApiConfig.getApiService().getTv()
        client.enqueue(object : Callback<ResponseItem> {
            override fun onResponse(
                call: Call<ResponseItem>,
                response: Response<ResponseItem>
            ) {
                if (response.isSuccessful) {
                    resultFilm.value = response.body()?.let { ApiResponse.success(it.results) }
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<ResponseItem>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }
        })

        return resultFilm
    }
}