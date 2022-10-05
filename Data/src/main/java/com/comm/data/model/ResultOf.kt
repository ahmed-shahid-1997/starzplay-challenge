package com.comm.data.model

sealed class ResultOf<T> {
    class Success<T>(val item: T) : ResultOf<T>()
    class Failure<T>(val message: String, val throwable: Throwable?) : ResultOf<T>()
}
