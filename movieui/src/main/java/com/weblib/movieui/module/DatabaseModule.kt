package com.weblib.movieui.module

import android.app.Application
import androidx.room.Room
import com.weblib.metadata.database.MovieDao
import com.weblib.metadata.database.PopularDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): PopularDatabase {
        return Room.databaseBuilder(application, PopularDatabase::class.java, "populars.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun providePopularsDao(database: PopularDatabase): MovieDao = database.movieDao

    single { provideDatabase(androidApplication()) }
    single { providePopularsDao(get()) }
}