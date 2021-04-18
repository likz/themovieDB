package com.weblib.apibusiness

import com.weblib.metadata.PopularListResponse
import com.weblib.metadata.RequestResult

interface PopularProvider {
    suspend fun getAllPopulars() : RequestResult<PopularListResponse>
}