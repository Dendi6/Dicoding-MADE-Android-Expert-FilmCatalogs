package com.dendi.filmscatalogs.di

import com.dendi.filmcatalogs.core.domain.usecase.FilmInteractor
import com.dendi.filmcatalogs.core.domain.usecase.FilmUseCase
import com.dendi.filmscatalogs.detail.DetailActivityViewModel
import com.dendi.filmscatalogs.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<FilmUseCase> { FilmInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailActivityViewModel(get()) }
}