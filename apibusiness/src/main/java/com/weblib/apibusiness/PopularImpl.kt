package com.weblib.apibusiness

import com.weblib.metadata.PopularListResponse
import com.weblib.metadata.RequestResult
import com.weblib.movieapi.PopularApi
import com.weblib.movieapi.handleApiError
import com.weblib.movieapi.handleSuccess

class PopularImpl (
    private val popularApi: PopularApi,
    private val connectivityChecker: ConnectivityChecker,
): PopularProvider{

    override suspend fun getAllPopulars(): RequestResult<PopularListResponse> {
        return if (connectivityChecker.isConnected()) {
            try {
                val response = popularApi.getAllPopulars()
                if (response.isSuccessful) {
                    handleSuccess(response)
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