package com.kevin.multiapiapp.data.api.mapbox

import com.kevin.multiapiapp.data.model.mapbox.MapboxRetrieveDTO
import com.kevin.multiapiapp.data.model.mapbox.MapboxSearchDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MapboxApi {

    /**
     * Searches for location suggestions based on a query string.
     *
     * @param query The search query (e.g., a place name or address).
     * @param language The language for the search results (default: "fr").
     * @param sessionToken A unique token for the search session to improve result relevance.
     * @param accessToken The access token for authenticating requests to the Mapbox API.
     * @return A [MapboxSearchDTO] containing a list of location suggestions.
     */
    @GET("$BASE_PATH/$SUGGEST")
    suspend fun searchLocation(
        @Query(PARAM_QUERY) query: String,
        @Query(PARAM_LANGUAGE) language: String = DEFAULT_LANGUAGE,
        @Query(PARAM_SESSION_TOKEN) sessionToken: String,
        @Query(PARAM_ACCESS_TOKEN) accessToken: String,
    ): MapboxSearchDTO

    /**
     * Retrieves detailed information about a specific location by its ID.
     *
     * @param id The unique identifier of the location.
     * @param sessionToken The session token used in the initial search for context.
     * @param accessToken The access token for authenticating requests to the Mapbox API.
     * @param language The language for the detailed information (default: "fr").
     * @return A [MapboxRetrieveDTO] containing detailed information about the location.
     */
    @GET("$BASE_PATH/$RETRIEVE/{$ID}")
    suspend fun retrieveLocationDetails(
        @Path(ID) id: String,
        @Query(PARAM_SESSION_TOKEN) sessionToken: String,
        @Query(PARAM_ACCESS_TOKEN) accessToken: String,
        @Query(PARAM_LANGUAGE) language: String = DEFAULT_LANGUAGE
    ): MapboxRetrieveDTO

    companion object {
        // Paths
        private const val BASE_PATH = "search/searchbox/v1"
        private const val SUGGEST = "suggest"
        private const val RETRIEVE = "retrieve"

        // Parameters
        private const val PARAM_QUERY = "q"
        private const val PARAM_LANGUAGE = "language"
        private const val PARAM_SESSION_TOKEN = "session_token"
        private const val PARAM_ACCESS_TOKEN = "access_token"
        private const val ID = "id"
        private const val DEFAULT_LANGUAGE = "fr"
    }
}