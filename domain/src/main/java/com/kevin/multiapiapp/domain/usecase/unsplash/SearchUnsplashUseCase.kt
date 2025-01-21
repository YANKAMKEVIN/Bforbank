package com.kevin.multiapiapp.domain.usecase.unsplash

import com.kevin.multiapiapp.domain.model.unsplash.UnsplashDomain
import kotlinx.coroutines.flow.Flow

interface SearchUnsplashUseCase {

    /**
     * Searches for photos on Unsplash based on a query and pagination details.
     *
     * @param query The search query string.
     * @param page The page number for paginated results.
     * @return A Flow emitting the search results as a domain model.
     */
    suspend operator fun invoke(
        query: String, page: Int
    ): Flow<UnsplashDomain>
}