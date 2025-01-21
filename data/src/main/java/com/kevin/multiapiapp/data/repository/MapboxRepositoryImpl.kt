package com.kevin.multiapiapp.data.repository

import com.kevin.multiapiapp.common.network.NetworkResponse
import com.kevin.multiapiapp.data.BuildConfig
import com.kevin.multiapiapp.data.datasource.mapbox.MapboxDataSource
import com.kevin.multiapiapp.data.mapper.mapbox.MapboxRetrieveMapper
import com.kevin.multiapiapp.data.mapper.mapbox.MapboxSearchMapper
import com.kevin.multiapiapp.domain.model.mapbox.MapboxRetrieveDomain
import com.kevin.multiapiapp.domain.model.mapbox.MapboxSearchDomain
import com.kevin.multiapiapp.domain.repository.MapboxRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Implementation of the [MapboxRepository] interface.
 * This class acts as a bridge between the domain layer and the Mapbox data source layer,
 * handling data transformation and network response management.
 */
class MapboxRepositoryImpl @Inject constructor(
    private val mapboxDataSource: MapboxDataSource,
) : MapboxRepository {

    /**
     * Searches for locations based on a query string using the Mapbox data source.
     *
     * @param query The search string used to find locations.
     * @param sessionToken A unique token for the session to ensure consistent results and tracking.
     * @return A [Flow] emitting a [MapboxSearchDomain] object containing the search results.
     */
    override suspend fun searchLocation(
        query: String,
        sessionToken: String
    ): Flow<MapboxSearchDomain> =
        flow {
            val accessToken = BuildConfig.MAPBOX_ACCESS_TOKEN
            if (accessToken.isEmpty()) {
                throw IllegalStateException("Access token is missing or invalid")
            }
            when (val response = mapboxDataSource.searchLocation(
                query = query,
                sessionToken = sessionToken,
                accessToken = accessToken
            )) {
                is NetworkResponse.Success -> emit(MapboxSearchMapper.mapToDomain(response.data))
                is NetworkResponse.Failure -> {
                    throw Exception("Error: ${response.error.message}")
                }
            }
        }

    /**
     * Retrieves detailed information about a specific location using the Mapbox data source.
     *
     * @param mapBoxId The unique identifier of the location to retrieve.
     * @param sessionToken A unique token for the session to ensure consistent results and tracking.
     * @return A [Flow] emitting a [MapboxRetrieveDomain] object containing the location details.
     */
    override suspend fun retrieveLocation(
        mapBoxId: String,
        sessionToken: String
    ): Flow<MapboxRetrieveDomain> =
        flow {
            val accessToken = BuildConfig.MAPBOX_ACCESS_TOKEN
            if (accessToken.isEmpty()) {
                throw IllegalStateException("Access token is missing or invalid")
            }
            when (val response = mapboxDataSource.retrieveLocationDetails(
                mapBoxId = mapBoxId,
                sessionToken = sessionToken,
                accessToken = accessToken
            )) {
                is NetworkResponse.Success -> emit(MapboxRetrieveMapper.mapToDomain(response.data))
                is NetworkResponse.Failure -> {
                    throw Exception("Error: ${response.error.message}")
                }
            }
        }
}
