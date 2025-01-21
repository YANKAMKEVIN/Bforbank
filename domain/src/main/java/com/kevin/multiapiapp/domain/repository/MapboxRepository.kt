package com.kevin.multiapiapp.domain.repository

import com.kevin.multiapiapp.domain.model.mapbox.MapboxRetrieveDomain
import com.kevin.multiapiapp.domain.model.mapbox.MapboxSearchDomain
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for interacting with Mapbox-related data.
 * This abstraction allows the domain layer to access location data without
 * depending directly on data source implementations.
 */
interface MapboxRepository {

    /**
     * Searches for locations based on a query string.
     *
     * @param query The search string used to find locations.
     * @param sessionToken A unique token for the session to ensure consistent results and tracking.
     * @return A [Flow] emitting a [MapboxSearchDomain] object containing the search results.
     */
    suspend fun searchLocation(query: String, sessionToken: String): Flow<MapboxSearchDomain>

    /**
     * Retrieves detailed information about a specific location.
     *
     * @param mapBoxId The unique identifier of the location to retrieve.
     * @param sessionToken A unique token for the session to ensure consistent results and tracking.
     * @return A [Flow] emitting a [MapboxRetrieveDomain] object containing the location details.
     */
    suspend fun retrieveLocation(
        mapBoxId: String,
        sessionToken: String
    ): Flow<MapboxRetrieveDomain>
}
