package com.weblib.movieapi

import com.weblib.metadata.RequestResult
import okhttp3.ResponseBody
import retrofit2.Response

fun <T> handleApiError(response: Response<T>): RequestResult.Error {
    val responseBody: ResponseBody? = response.errorBody()?.apply { close() }
    val code: Int = response.code()
    return RequestResult.Error(Exception("[Response error $code]: ${responseBody?.string()}"))
}

fun <T> handleSuccess(response: Response<T>): RequestResult<T> {
    return response.body()?.let { RequestResult.Success(it) } ?: handleApiError(response)
}