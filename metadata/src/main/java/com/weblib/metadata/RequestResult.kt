package com.weblib.metadata

sealed class RequestResult<out T> {
    data class Success<out T>(val successData: T) : RequestResult<T>()
    class Error(
        private val exception: Exception,
        val message: String? = exception.localizedMessage
    ) : RequestResult<Nothing>()
}