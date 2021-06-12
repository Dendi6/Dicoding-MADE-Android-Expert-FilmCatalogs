package com.dendi.filmcatalogs.core.data

import com.dendi.filmcatalogs.core.data.source.local.LocalDataSource
import com.dendi.filmcatalogs.core.data.source.remote.ApiResponse
import com.dendi.filmcatalogs.core.data.source.remote.RemoteDataSource
import com.dendi.filmcatalogs.core.data.source.remote.response.ListResponse
import com.dendi.filmcatalogs.core.domain.model.Film
import com.dendi.filmcatalogs.core.domain.repository.IFilmRepository
import com.dendi.filmcatalogs.core.utils.AppExecutors
import com.dendi.filmcatalogs.core.utils.DataMapper
import com.dendi.filmcatalogs.core.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FilmRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IFilmRepository {
    override fun getAllMovies(): Flow<Resource<List<Film>>> {
        return object :
            com.dendi.filmcatalogs.core.data.NetworkBoundResource<List<Film>, List<ListResponse>>() {
            public override fun loadFromDB(): Flow<List<Film>> {
                return localDataSource.getMovies().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Film>?): Boolean =
                data == null || data.isEmpty()

            public override suspend fun createCall(): Flow<ApiResponse<List<ListResponse>>> =
                remoteDataSource.getAllMovies()

            public override suspend fun saveCallResult(data: List<ListResponse>) {
                val dataList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertFilm(dataList)
            }
        }.asFlow()
    }

    override fun getAllTvShow(): Flow<Resource<List<Film>>> {
        return object :
            com.dendi.filmcatalogs.core.data.NetworkBoundResource<List<Film>, List<ListResponse>>() {
            public override fun loadFromDB(): Flow<List<Film>> {
                return localDataSource.getTvShow().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Film>?): Boolean =
                data == null || data.isEmpty()

            public override suspend fun createCall(): Flow<ApiResponse<List<ListResponse>>> =
                remoteDataSource.getAllTvShow()

            public override suspend fun saveCallResult(data: List<ListResponse>) {
                val dataList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertFilm(dataList)
            }
        }.asFlow()
    }

    override fun getFavorited(): Flow<List<Film>> {
        return localDataSource.getFavorited().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFilmFavorite(film: Film, state: Boolean) {
        val filmsEntity = DataMapper.mapDomainToEntity(film)
        appExecutors.diskIO().execute { localDataSource.setFilmFavorite(filmsEntity, state) }
    }

}