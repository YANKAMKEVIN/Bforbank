package com.kevin.multiapiapp.data.datasource.mapbox.impl

import com.kevin.multiapiapp.common.network.NetworkResponse
import com.kevin.multiapiapp.common.network.NetworkUtil
import com.kevin.multiapiapp.data.api.mapbox.MapboxApi
import com.kevin.multiapiapp.data.datasource.mapbox.MapboxDataSource
import com.kevin.multiapiapp.data.model.mapbox.MapboxRetrieveDTO
import com.kevin.multiapiapp.data.model.mapbox.MapboxSearchDTO
import javax.inject.Inject

/**
 * Implementation of the [MapboxDataSource] interface.
 * This class communicates with the Mapbox API to fetch location search results
 * and detailed location information.
 */
class MapboxDataSourceImpl @Inject constructor(
    private val mapboxApi: MapboxApi
) : MapboxDataSource {

    /**
     * Searches for locations based on a query string using the Mapbox API.
     *
     * @param query The search string to find locations.
     * @param sessionToken A unique token for the session to ensure consistent results and tracking.
     * @param accessToken The Mapbox access token for authenticating the API request.
     * @return A [NetworkResponse] wrapping a [MapboxSearchDTO] with the search results.
     */
    override suspend fun searchLocation(
        query: String,
        sessionToken: String,
        accessToken: String
    ): NetworkResponse<MapboxSearchDTO> =
        NetworkUtil.executeApiCall {
            mapboxApi.searchLocation(
                query = query,
                sessionToken = sessionToken,
                accessToken = accessToken
            )
        }

    /**
     * Retrieves detailed information about a specific location from the Mapbox API.
     *
     * @param mapBoxId The unique identifier of the location to retrieve.
     * @param sessionToken A unique token for the session to ensure consistent results and tracking.
     * @param accessToken The Mapbox access token for authenticating the API request.
     * @return A [NetworkResponse] wrapping a [MapboxRetrieveDTO] with the location details.
     */
    override suspend fun retrieveLocationDetails(
        mapBoxId: String,
        sessionToken: String,
        accessToken: String
    ): NetworkResponse<MapboxRetrieveDTO> =
        NetworkUtil.executeApiCall {
            mapboxApi.retrieveLocationDetails(
                id = mapBoxId,
                sessionToken = sessionToken,
                accessToken = accessToken
            )
        }

}