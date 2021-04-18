package com.weblib.movieui

import com.weblib.movieapi.PopularApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single<PopularApi> {
        get<Retrofit>().create(PopularApi::class.java)
    }
}