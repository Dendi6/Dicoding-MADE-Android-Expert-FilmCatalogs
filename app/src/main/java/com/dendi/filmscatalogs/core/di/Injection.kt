package com.dendi.filmscatalogs.core.di

import android.content.Context
import com.dendi.filmscatalogs.core.data.FilmRepository
import com.dendi.filmscatalogs.core.data.source.local.LocalDataSource
import com.dendi.filmscatalogs.core.data.source.local.room.FilmDatabase
import com.dendi.filmscatalogs.core.data.source.remote.RemoteDataSource
import com.dendi.filmscatalogs.core.data.source.remote.network.ApiConfig
import com.dendi.filmscatalogs.core.domain.repository.IFilmRepository
import com.dendi.filmscatalogs.core.domain.usecase.FilmInteractor
import com.dendi.filmscatalogs.core.domain.usecase.FilmUseCase
import com.dendi.filmscatalogs.core.utils.AppExecutors

object Injection {

    private fun provideRepository(context: Context):IFilmRepository {
        val database = FilmDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.getApiService())
        val localDataSource = LocalDataSource.getInstance(database.filmDao())
        val appExecutors = AppExecutors()

        return FilmRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideFilmUseCase(context: Context): FilmUseCase {
        val repository = provideRepository(context)
        return FilmInteractor(repository)
    }
}