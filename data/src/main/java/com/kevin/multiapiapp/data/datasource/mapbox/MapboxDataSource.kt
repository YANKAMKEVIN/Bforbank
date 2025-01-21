package com.kevin.multiapiapp.data.datasource.mapbox

import com.kevin.multiapiapp.common.network.NetworkResponse
import com.kevin.multiapiapp.data.model.mapbox.MapboxRetrieveDTO
import com.kevin.multiapiapp.data.model.mapbox.MapboxSearchDTO

/**
 * Interface defining the data source operations for interacting with the Mapbox API.
 * This abstraction allows flexible implementations for querying location-related data.
 */
interface MapboxDataSource {

    /**
     * Searches for locations based on a query string.
     *
     * @param query The search string to find locations.
     * @param sessionToken A unique token for the session to ensure consistent results and tracking.
     * @param accessToken The Mapbox access token for authenticating the API request.
     * @return A [NetworkResponse] wrapping a [MapboxSearchDTO] with the search results.
     */
    suspend fun searchLocation(
        query: String,
        sessionToken: String,
        accessToken: String
    ): NetworkResponse<MapboxSearchDTO>

    /**
     * Retrieves detailed information about a specific location.
     *
     * @param mapBoxId The unique identifier of the location to retrieve.
     * @param sessionToken A unique token for the session to ensure consistent results and tracking.
     * @param accessToken The Mapbox access token for authenticating the API request.
     * @return A [NetworkResponse] wrapping a [MapboxRetrieveDTO] with the location details.
     */
    suspend fun retrieveLocationDetails(
        mapBoxId: String,
        sessionToken: String,
        accessToken: String
    ): NetworkResponse<MapboxRetrieveDTO>
}
