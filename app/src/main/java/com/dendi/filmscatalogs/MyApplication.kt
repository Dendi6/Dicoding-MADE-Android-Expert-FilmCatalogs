package com.dendi.filmscatalogs

import android.app.Application
import com.dendi.filmcatalogs.core.di.databaseModule
import com.dendi.filmcatalogs.core.di.networkModule
import com.dendi.filmcatalogs.core.di.repositoryModule
import com.dendi.filmscatalogs.di.useCaseModule
import com.dendi.filmscatalogs.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}