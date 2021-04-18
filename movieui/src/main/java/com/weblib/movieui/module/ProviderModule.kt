package com.weblib.movieui

import com.weblib.apibusiness.PopularImpl
import com.weblib.apibusiness.PopularProvider
import org.koin.dsl.module

val providerModule = module {
    single<PopularProvider> { PopularImpl(get(), get()) }
}