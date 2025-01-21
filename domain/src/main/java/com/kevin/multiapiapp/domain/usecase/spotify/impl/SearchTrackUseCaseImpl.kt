package com.kevin.multiapiapp.domain.usecase.spotify.impl

import com.kevin.multiapiapp.domain.model.spotify.SpotifySearchDomain
import com.kevin.multiapiapp.domain.repository.SpotifyRepository
import com.kevin.multiapiapp.domain.usecase.spotify.SearchTracksUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Implementation of the [SearchTracksUseCase] interface.
 *
 * This class is responsible for invoking the repository's method to search for Spotify tracks
 * and acts as a bridge between the domain and data layers.
 * It delegates the actual data fetching to the [SpotifyRepository].
 */
internal class SearchTrackUseCaseImpl(
    private val spotifyRepository: SpotifyRepository
) : SearchTracksUseCase {
    /**
     * Invokes the search operation for tracks using the [SpotifyRepository].
     * This method will fetch the results from the data source and return them as a [Flow].
     *
     * @param query The search term used to find tracks on Spotify.
     * @param offset The offset for pagination, indicating where to start fetching data.
     * @return A [Flow] emitting the search results or an empty state if an error occurs.
     */
    override suspend operator fun invoke(query: String, offset: Int): Flow<SpotifySearchDomain> =
        spotifyRepository.searchTracks(query, offset)
}