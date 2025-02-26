package com.nguyenmoclam.androidessentialsguide.data

sealed class DataResponse<T> {
    data class Success<T>(val data: T) : DataResponse<T>()

    data class Error<T>(val message: Throwable) : DataResponse<T>()
}
