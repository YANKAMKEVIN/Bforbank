package com.kevin.multiapiapp.data.api.spotify

import com.kevin.multiapiapp.data.BuildConfig
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpotifyAuthInterceptor @Inject constructor(
    private val spotifyAuthApi: SpotifyAuthApi,
) : Interceptor {

    private val clientId = BuildConfig.SPOTIFY_CLIENT_ID
    private val clientSecret = BuildConfig.SPOTIFY_CLIENT_SECRET
    private var accessToken: String? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        // refresh if token is null or expired
        if (accessToken == null) {
            refreshAccessToken()
        }

        val authenticatedRequest = request.newBuilder()
            .addHeader("Authorization", "Bearer $accessToken")
            .build()

        val response = chain.proceed(authenticatedRequest)

        // Retry if 401 Unauthorized
        if (response.code() == 401) {
            refreshAccessToken()
            val retriedRequest = request.newBuilder()
                .addHeader("Authorization", "Bearer $accessToken")
                .build()
            return chain.proceed(retriedRequest)
        }

        return response
    }

    private fun refreshAccessToken() {
        val credentials = "$clientId:$clientSecret"
        val basicAuth = "Basic ${
            android.util.Base64.encodeToString(
                credentials.toByteArray(),
                android.util.Base64.NO_WRAP
            )
        }"

        runBlocking {
            try {
                val response = spotifyAuthApi.getAccessToken(
                    authorization = basicAuth
                )
                accessToken = response.accessToken
            } catch (e: Exception) {
                e.printStackTrace()
                throw IllegalStateException("Failed to refresh Spotify access token", e)
            }
        }
    }
}