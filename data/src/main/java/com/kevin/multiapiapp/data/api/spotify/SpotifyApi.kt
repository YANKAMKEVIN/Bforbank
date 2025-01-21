package com.kevin.multiapiapp.data.api.spotify

import com.kevin.multiapiapp.data.model.spotify.SpotifySearchDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface SpotifyApi {

    /**
     * Searches for tracks on Spotify based on a query string.
     *
     * @param query The search query, such as an artist name or track title.
     * @param type The type of search, default is "track". You can change it to "album", "artist", or "playlist".
     * @param limit The number of results to return (default is 10).
     * @param offset The number of results to skip for pagination (default is 0).
     * @return A [SpotifySearchDTO] containing the search results.
     */
    @GET(BASE_PATH)
    suspend fun searchTracks(
        @Query(PARAM_QUERY) query: String,
        @Query(PARAM_TYPE) type: String = TYPE_TRACK,
        @Query(PARAM_LIMIT) limit: Int = DEFAULT_LIMIT,
        @Query(PARAM_OFFSET) offset: Int = DEFAULT_OFFSET
    ): SpotifySearchDTO

    companion object {
        //Path
        private const val BASE_PATH = "v1/search"

        // Parameters
        private const val PARAM_QUERY = "q"
        private const val PARAM_TYPE = "type"
        private const val PARAM_LIMIT = "limit"
        private const val PARAM_OFFSET = "offset"

        // Default values
        private const val TYPE_TRACK = "track"
        private const val DEFAULT_LIMIT = 10
        private const val DEFAULT_OFFSET = 0
    }
}