package com.dendi.filmcatalogs.core.data.source.remote

sealed class ApiResponse<out R> {
    data class Success<out T>(val data: T) : com.dendi.filmcatalogs.core.data.source.remote.ApiResponse<T>()
    data class Error(val errorMessage: String) : com.dendi.filmcatalogs.core.data.source.remote.ApiResponse<Nothing>()
    object Empty : com.dendi.filmcatalogs.core.data.source.remote.ApiResponse<Nothing>()
}