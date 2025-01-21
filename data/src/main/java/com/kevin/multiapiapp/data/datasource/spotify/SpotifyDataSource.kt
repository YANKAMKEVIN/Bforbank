package com.kevin.multiapiapp.data.datasource.spotify

import com.kevin.multiapiapp.common.network.NetworkResponse
import com.kevin.multiapiapp.data.model.spotify.SpotifySearchDTO

/**
 * Defines the contract for the data source that interacts with the Spotify API.
 * This interface provides methods to perform Spotify-specific network operations.
 */
interface SpotifyDataSource {
    /**
     * Searches for tracks on Spotify based on a query and pagination.
     *
     * @param query The search term used to find tracks.
     * @param offset The offset to paginate results.
     * @return A [NetworkResponse] wrapping a [SpotifySearchDTO] containing the search results.
     */
    suspend fun searchTracks(query: String, offset: Int): NetworkResponse<SpotifySearchDTO>
}