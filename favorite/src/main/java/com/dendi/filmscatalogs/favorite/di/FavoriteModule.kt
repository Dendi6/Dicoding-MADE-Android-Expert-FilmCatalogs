package com.dendi.filmscatalogs.favorite.di

import com.dendi.filmscatalogs.favorite.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}