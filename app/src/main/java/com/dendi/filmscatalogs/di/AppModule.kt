package com.dendi.filmscatalogs.di

import com.dendi.filmscatalogs.core.domain.usecase.FilmInteractor
import com.dendi.filmscatalogs.core.domain.usecase.FilmUseCase
import com.dendi.filmscatalogs.detail.DetailActivityViewModel
import com.dendi.filmscatalogs.favorite.FavoriteViewModel
import com.dendi.filmscatalogs.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<FilmUseCase> { FilmInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailActivityViewModel(get()) }
}