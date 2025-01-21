package com.kevin.multiapiapp.domain.usecase.mapbox

import com.kevin.multiapiapp.domain.model.mapbox.MapboxRetrieveDomain
import kotlinx.coroutines.flow.Flow

/**
 * Use case interface for retrieving detailed information about a specific location.
 * This abstraction ensures that the implementation of this functionality remains
 * decoupled from other layers.
 */
interface RetrieveLocationDetailsUseCase {
    /**
     * Retrieves detailed information for a location identified by its Mapbox ID.
     *
     * @param mapBoxId The unique identifier of the location to retrieve.
     * @param sessionToken A unique session token to ensure consistent results.
     * @return A [Flow] emitting a [MapboxRetrieveDomain] object containing location details.
     */
    suspend operator fun invoke(
        mapBoxId: String,
        sessionToken: String
    ): Flow<MapboxRetrieveDomain>
}