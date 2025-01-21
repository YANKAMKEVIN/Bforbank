package com.kevin.multiapiapp.data.datasource.spotify.impl

import com.kevin.multiapiapp.common.network.NetworkResponse
import com.kevin.multiapiapp.common.network.NetworkUtil
import com.kevin.multiapiapp.data.api.spotify.SpotifyApi
import com.kevin.multiapiapp.data.datasource.spotify.SpotifyDataSource
import com.kevin.multiapiapp.data.model.spotify.SpotifySearchDTO
import javax.inject.Inject

/**
 * Implementation of the [SpotifyDataSource] interface.
 * This class is responsible for executing network calls to the Spotify API
 * and mapping the response into a [NetworkResponse] object.
 */
class SpotifyDataSourceImpl @Inject constructor(
    private val spotifyApi: SpotifyApi
) : SpotifyDataSource {

    /**
     * Searches for tracks on Spotify by invoking the API.
     *
     * @param query The search term to find matching tracks.
     * @param offset The offset for paginated results.
     * @return A [NetworkResponse] containing either the result of the search or an error.
     */
    override suspend fun searchTracks(
        query: String,
        offset: Int
    ): NetworkResponse<SpotifySearchDTO> =
        NetworkUtil.executeApiCall {
            spotifyApi.searchTracks(
                query = query,
                offset = offset
            )
        }

}
