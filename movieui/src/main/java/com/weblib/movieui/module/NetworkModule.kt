package com.weblib.movieui

import com.weblib.apibusiness.ConnectivityChecker
import com.weblib.apibusiness.ConnectivityCheckerImpl
import com.weblib.movieapi.RequestInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    val connectTimeout : Long = 40// 20s
    val readTimeout : Long  = 40 // 20s

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS).apply {
                addInterceptor(RequestInterceptor())
            }.build()
    }
    single<Retrofit>{
        val baseUrl = "https://api.themoviedb.org/"
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(get())
            .build()
    }

    single<ConnectivityChecker>{
        ConnectivityCheckerImpl(get())
    }
}