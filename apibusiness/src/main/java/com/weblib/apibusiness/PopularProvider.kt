package com.weblib.apibusiness

import com.weblib.metadata.Movie
import com.weblib.metadata.RequestResult

interface PopularProvider {
    suspend fun getAllPopulars(page: Int): RequestResult<List<Movie>>
}