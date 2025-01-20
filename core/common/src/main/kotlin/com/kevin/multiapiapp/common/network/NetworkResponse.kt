package com.kevin.multiapiapp.common.network

/**
 * Wrapper for data provided from the MYM server or Mock
 */
sealed class NetworkResponse<out T : Any> {
    /**
     * response with a 2xx status code
     */
    data class Success<out T : Any>(val data: T) : NetworkResponse<T>()

    /**
     * response with a non-2xx status code.
     */
    data class Failure(val error: NetworkError) : NetworkResponse<Nothing>()
}