package com.nguyenmoclam.androidessentialsguide.data

sealed class DataResponse<out T> {
    data class Success<out T>(val data: T) : DataResponse<T>()

    data class Error(val message: Throwable) : DataResponse<Nothing>()
}
