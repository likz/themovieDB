package com.weblib.apibusiness

import com.weblib.metadata.Movie
import com.weblib.metadata.RequestResult
import com.weblib.metadata.database.MovieDao
import com.weblib.movieapi.PopularApi
import com.weblib.movieapi.handleApiError

class PopularImpl(
    private val popularApi: PopularApi,
    private val connectivityChecker: ConnectivityChecker,
    private val movieDao: MovieDao,
) : PopularProvider {

    override suspend fun getAllPopulars(page: Int): RequestResult<List<Movie>> {
        val popularList = movieDao.getMovieList(page)
        if (popularList.isNotEmpty()) {
            return RequestResult.Success(popularList)
        }

        return if (connectivityChecker.isConnected()) {
            try {
                val response = popularApi.getAllPopulars(page)
                if (response.isSuccessful) {
                    return response.body()?.let {
                        val result = it.results.map { movie ->
                            movie.page = page
                            movie
                        }
                        movieDao.insertMovieList(result)
                        RequestResult.Success(result)
                    } ?: handleApiError(response)
                } else {
                    handleApiError(response)
                }
            } catch (e: Exception) {
                RequestResult.Error(e)
            }
        } else {
            RequestResult.Error(Exception("No Network Connectivity"))
        }
    }
}