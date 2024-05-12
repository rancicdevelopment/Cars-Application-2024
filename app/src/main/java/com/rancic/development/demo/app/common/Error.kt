package com.rancic.development.demo.app.common

import androidx.annotation.StringRes


data class Error(
    val statusCode: Int = 0,
    val message: String? = null,
    val code: Int = 0,
    @StringRes val errorMessage: Int? = null
) {

    companion object {
        val UNKNOWN_ERROR = Error(1000, null, 0)
    }
}