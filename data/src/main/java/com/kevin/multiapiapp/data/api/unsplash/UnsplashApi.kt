package com.kevin.multiapiapp.data.api.unsplash

import com.kevin.multiapiapp.data.model.unsplash.UnsplashResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {

    /**
     * Searches for photos on Unsplash based on a query and pagination parameters.
     *
     * @param query The search term to find matching photos.
     * @param page The page number to retrieve (for pagination).
     * @param perPage The number of results per page (default is 20).
     * @param clientId The API client ID required for authentication.
     * @return A [UnsplashResponseDTO] containing the search results.
     */
    @GET(BASE_PATH)
    suspend fun searchPhotos(
        @Query(PARAM_QUERY) query: String,
        @Query(PARAM_PAGE) page: Int,
        @Query(PARAM_PER_PAGE) perPage: Int = DEFAULT_PER_PAGE,
        @Query(PARAM_CLIENT_ID) clientId: String
    ): UnsplashResponseDTO

    companion object {
        //Path
        private const val BASE_PATH = "/search/photos"

        // Query parameters
        private const val PARAM_QUERY = "query"
        private const val PARAM_PAGE = "page"
        private const val PARAM_PER_PAGE = "per_page"
        private const val PARAM_CLIENT_ID = "client_id"

        // Default values
        private const val DEFAULT_PER_PAGE = 20
    }
}
