package com.weblib.movieui.module

import com.weblib.movieapi.PopularApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single<PopularApi> {
        get<Retrofit>().create(PopularApi::class.java)
    }
}