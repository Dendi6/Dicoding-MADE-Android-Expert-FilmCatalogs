package com.dendi.filmscatalogs.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.dendi.filmscatalogs.core.data.source.local.LocalDataSource
import com.dendi.filmscatalogs.core.data.source.local.entity.ListEntity
import com.dendi.filmscatalogs.core.data.source.remote.ApiResponse
import com.dendi.filmscatalogs.core.data.source.remote.RemoteDataSource
import com.dendi.filmscatalogs.core.data.source.remote.response.ListResponse
import com.dendi.filmscatalogs.core.domain.model.Film
import com.dendi.filmscatalogs.core.domain.repository.IFilmRepository
import com.dendi.filmscatalogs.core.utils.AppExecutors
import com.dendi.filmscatalogs.core.utils.DataMapper
import com.dendi.filmscatalogs.core.vo.Resource

class FilmRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IFilmRepository {
    companion object {
        @Volatile
        private var instance: FilmRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): FilmRepository =
            instance ?: synchronized(this) {
                instance ?: FilmRepository(remoteData, localData, appExecutors).apply {
                    instance = this
                }
            }
    }

    override fun getAllMovies(): LiveData<Resource<List<Film>>> {
        return object :
            NetworkBoundResource<List<Film>, List<ListResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<Film>> {
                return Transformations.map(localDataSource.getMovies()) {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Film>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<ListResponse>>> =
                remoteDataSource.getAllMovies()

            public override fun saveCallResult(data: List<ListResponse>) {
                val listItem = ArrayList<ListEntity>()
                for (response in data) {
                    val item = ListEntity(
                        response.id,
                        response.title,
                        response.posterPath,
                        response.backdropPath,
                        response.overview,
                        false,
                        response.mediaType
                    )

                    item.let { listItem.add(it) }
                }

                localDataSource.insertFilm(listItem)
            }
        }.asLiveData()
    }

    override fun getAllTvShow(): LiveData<Resource<List<Film>>> {
        return object :
            NetworkBoundResource<List<Film>, List<ListResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<Film>> {
                return Transformations.map(localDataSource.getTvShow()) {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Film>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<ListResponse>>> =
                remoteDataSource.getAllTvShow()

            public override fun saveCallResult(data: List<ListResponse>) {
                val listItem = ArrayList<ListEntity>()
                for (response in data) {
                    val item = ListEntity(
                        response.id,
                        response.name,
                        response.posterPath,
                        response.backdropPath,
                        response.overview,
                        false,
                        response.mediaType
                    )

                    item.let { listItem.add(it) }
                }

                localDataSource.insertFilm(listItem)
            }
        }.asLiveData()
    }

    override fun getFavorited(): LiveData<List<Film>> {
        return Transformations.map(localDataSource.getFavorited()) {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFilmFavorite(film: Film, state: Boolean) {
        val filmsEntity = DataMapper.mapDomainToEntity(film)
        appExecutors.diskIO().execute { localDataSource.setFilmFavorite(filmsEntity, state) }
    }

}