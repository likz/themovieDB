package com.weblib.movieui.module

import com.weblib.movieui.viewmodel.MovieDetailViewModel
import com.weblib.movieui.viewmodel.PopularViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel {
        PopularViewModel(popularProvider = get())
    }
    viewModel {
        MovieDetailViewModel(movieDao = get())
    }
}