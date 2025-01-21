package com.kevin.multiapiapp.domain.usecase.mapbox.impl

import com.kevin.multiapiapp.domain.model.mapbox.MapboxRetrieveDomain
import com.kevin.multiapiapp.domain.repository.MapboxRepository
import com.kevin.multiapiapp.domain.usecase.mapbox.RetrieveLocationDetailsUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Implementation of the [RetrieveLocationDetailsUseCase] interface.
 * This class coordinates the retrieval of detailed location information
 * from the Mapbox repository.
 *
 * @property mapboxRepository The repository used to interact with Mapbox data sources.
 */
internal class RetrieveLocationDetailsUseCaseImpl(
    private val mapboxRepository: MapboxRepository
) : RetrieveLocationDetailsUseCase {
    /**
     * Invokes the use case to retrieve location details for a specific Mapbox ID.
     *
     * @param mapBoxId The unique identifier of the location to retrieve.
     * @param sessionToken A unique session token to ensure consistency in results.
     * @return A [Flow] emitting a [MapboxRetrieveDomain] object with the location details.
     */
    override suspend fun invoke(
        mapBoxId: String,
        sessionToken: String
    ): Flow<MapboxRetrieveDomain> =
        mapboxRepository.retrieveLocation(mapBoxId, sessionToken)
}