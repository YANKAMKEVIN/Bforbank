package com.kevin.multiapiapp.domain.repository

import com.kevin.multiapiapp.domain.model.spotify.SpotifySearchDomain
import kotlinx.coroutines.flow.Flow

/**
 * Defines the contract for the repository that handles the business logic
 * and interacts with the data sources to fetch Spotify-related data.
 */
interface SpotifyRepository {

    /**
     * Searches for tracks based on a query and pagination offset.
     *
     * @param query The search term to find tracks.
     * @param offset The offset for pagination.
     * @return A [Flow] emitting the search results or an empty state if an error occurs.
     */
    suspend fun searchTracks(query: String, offset: Int): Flow<SpotifySearchDomain>
}
