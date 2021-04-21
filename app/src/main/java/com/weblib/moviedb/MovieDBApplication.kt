package com.weblib.moviedb

import android.app.Application
import com.weblib.movieui.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MovieDBApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MovieDBApplication)
            modules(
                networkModule,
                apiModule,
                viewModelsModule,
                providerModule,
                databaseModule
            )
        }
    }
}