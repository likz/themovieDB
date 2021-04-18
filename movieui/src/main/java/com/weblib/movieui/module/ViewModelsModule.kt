package com.weblib.movieui

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel {
        PopularViewModel(popularProvider = get())
    }
}