package com.rancic.development.demo.app.common

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
data class Result<out T>(
    val status: Status,
    val data: T?,
    val error: Error? = null,
    val message: String? = null
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?): Result<T> {
            return Result(Status.SUCCESS, data, null, null)
        }

        fun <T> error(error: Error, message: String? = null, data: T? = null): Result<T> {
            return Result(Status.ERROR, data, error, message)
        }

        fun <T> loading(data: T? = null): Result<T> {
            return Result(Status.LOADING, data, null, null)
        }
    }
}