package com.kevin.multiapiapp.domain.usecase.spotify

import com.kevin.multiapiapp.domain.model.spotify.SpotifySearchDomain
import kotlinx.coroutines.flow.Flow

/**
 * Defines the contract for the use case that performs a search for Spotify tracks
 * based on a given query and pagination offset.
 *
 * This use case encapsulates the business logic for searching tracks and
 * abstracts away the underlying data retrieval.
 */
interface SearchTracksUseCase {

    /**
     * Executes the search for tracks based on the provided query and offset.
     *
     * @param query The search term used to find tracks on Spotify.
     * @param offset The offset for pagination, which helps in fetching subsequent results.
     * @return A [Flow] emitting the search results in the form of [SpotifySearchDomain],
     *         or an empty state if an error occurs.
     */
    suspend operator fun invoke(query: String, offset: Int): Flow<SpotifySearchDomain>
}