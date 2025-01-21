package com.kevin.multiapiapp.domain.usecase.mapbox

import com.kevin.multiapiapp.domain.model.mapbox.MapboxSearchDomain
import kotlinx.coroutines.flow.Flow

/**
 * Use case interface for searching for locations based on a query string.
 * This abstraction allows the implementation to be decoupled from the
 * underlying data retrieval logic.
 */
interface SearchLocationUseCase {

    /**
     * Searches for locations using a query string and session token.
     *
     * @param query The search string used to find locations.
     * @param sessionToken A unique session token to ensure consistent results.
     * @return A [Flow] emitting a [MapboxSearchDomain] object containing the search results.
     */
    suspend operator fun invoke(query: String, sessionToken: String): Flow<MapboxSearchDomain>
}
