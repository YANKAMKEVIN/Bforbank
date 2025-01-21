package com.kevin.multiapiapp.common.network

import retrofit2.HttpException
import java.io.IOException

object NetworkUtil {
    /**
     * Executes a network call and wraps the response in a [NetworkResponse].
     *
     * This function handles network errors and exceptions by mapping them to appropriate
     * [NetworkError] types. It returns a [NetworkResponse] indicating either a successful
     * result or a failure due to an error.
     *
     * @param apiCall A suspend function that represents the network call.
     * @param T The type of the response data returned from the API call.
     * @return A [NetworkResponse] containing either a [Success] or [Failure] result.
     */
    suspend fun <T : Any> executeApiCall(
        apiCall: suspend () -> T,
    ): NetworkResponse<T> {
        return try {
            // Execute the network call
            val response = apiCall()
            // Return the response as a successful result
            NetworkResponse.Success(response)
        } catch (e: HttpException) {
            // Handle HTTP exceptions like 4xx, 5xx
            NetworkResponse.Failure(mapError(e))
        } catch (e: IOException) {
            // Handle IOExceptions (e.g., network errors)
            NetworkResponse.Failure(NetworkError.Unknown(e.message ?: "Network Error"))
        } catch (e: Exception) {
            // Catch any other exceptions and emit an unknown error
            NetworkResponse.Failure(NetworkError.Unknown(e.message ?: "Unknown Error"))
        }
    }

    /**
     * Maps an [HttpException] to a corresponding [NetworkError].
     *
     * This method translates HTTP status codes to specific error types
     * defined in the [NetworkError] class.
     *
     * @param exception The [HttpException] thrown during the API call.
     * @return A corresponding [NetworkError] based on the HTTP status code.
     */
    private fun mapError(exception: HttpException): NetworkError {
        return when (exception.code()) {
            400 -> NetworkError.BadRequest(exception.message ?: "Bad Request")
            401 -> NetworkError.Unauthorized(exception.message ?: "Unauthorized")
            403 -> NetworkError.Forbidden(exception.message ?: "Forbidden")
            404 -> NetworkError.NotFound(exception.message ?: "Not Found")
            500 -> NetworkError.InternalServerError(exception.message ?: "Internal Server Error")
            else -> NetworkError.Unknown(exception.message ?: "Unknown HTTP Error")
        }
    }
}