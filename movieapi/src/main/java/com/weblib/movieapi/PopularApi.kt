package com.weblib.movieapi

import com.weblib.metadata.PopularListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularApi {
    /**
     * [Movie Keywords](https://developers.themoviedb.org/3/movies/get-popular-movies)
     *
     * Get a list of the popular movies on TMDB
     *
     * @param [page] Specify the page of results to query.
     *
     * @return [PopularListResponse] response
     */
    @GET("/3/movie/popular")
    suspend fun getAllPopulars(@Query("page") page: Int = 1): Response<PopularListResponse>
}
