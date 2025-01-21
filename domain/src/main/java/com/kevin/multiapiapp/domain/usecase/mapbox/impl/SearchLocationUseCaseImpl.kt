package com.kevin.multiapiapp.domain.usecase.mapbox.impl

import com.kevin.multiapiapp.domain.model.mapbox.MapboxSearchDomain
import com.kevin.multiapiapp.domain.repository.MapboxRepository
import com.kevin.multiapiapp.domain.usecase.mapbox.SearchLocationUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Implementation of the [SearchLocationUseCase] interface.
 * This class is responsible for managing the search for locations
 * through the Mapbox repository.
 *
 * @property mapboxRepository The repository used to interact with Mapbox data sources.
 */
internal class SearchLocationUseCaseImpl(
    private val mapboxRepository: MapboxRepository
) : SearchLocationUseCase {

    /**
     * Invokes the use case to search for locations based on a query string.
     *
     * @param query The search string used to find locations.
     * @param sessionToken A unique session token to ensure consistent results.
     * @return A [Flow] emitting a [MapboxSearchDomain] object containing the search results.
     */
    override suspend fun invoke(query: String, sessionToken: String): Flow<MapboxSearchDomain> =
        mapboxRepository.searchLocation(query, sessionToken)
}
