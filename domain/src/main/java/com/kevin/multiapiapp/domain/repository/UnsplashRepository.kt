package com.kevin.multiapiapp.domain.repository

import com.kevin.multiapiapp.domain.model.unsplash.UnsplashDomain
import kotlinx.coroutines.flow.Flow

/**
 * Defines the contract for the repository that handles the business logic
 * and interacts with the data sources to fetch Unsplash-related data.
 */
interface UnsplashRepository {
    /**
     * Searches photos on Unsplash.
     *
     * @param query The search query.
     * @param page The page number of the results.
     * @return A flow containing the search results as a domain model.
     */
    suspend fun searchPhotos(query: String, page: Int): Flow<UnsplashDomain>
}