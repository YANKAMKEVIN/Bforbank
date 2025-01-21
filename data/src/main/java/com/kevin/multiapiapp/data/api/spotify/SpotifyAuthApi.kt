package com.kevin.multiapiapp.data.api.spotify

import com.kevin.multiapiapp.data.model.spotify.SpotifyAuthResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface SpotifyAuthApi {

    /**
     * Gets an access token from Spotify using the client credentials grant type.
     *
     * @param authorization The authorization header in the format "Basic {encoded_client_credentials}".
     * @param grantType The grant type for the token request, default is "client_credentials".
     * @return A [SpotifyAuthResponse] containing the access token and other related information.
     */
    @FormUrlEncoded
    @POST(BASE_PATH)
    suspend fun getAccessToken(
        @Header(HEADER_AUTHORIZATION) authorization: String,
        @Field(PARAM_GRANT_TYPE) grantType: String = GRANT_TYPE_CLIENT_CREDENTIALS
    ): SpotifyAuthResponse

    companion object {
        //Path
        private const val BASE_PATH = "api/token"

        // Header and Field parameters
        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val PARAM_GRANT_TYPE = "grant_type"

        // Default values
        private const val GRANT_TYPE_CLIENT_CREDENTIALS = "client_credentials"
    }
}