package com.kevin.multiapiapp.domain.usecase.unsplash.impl

import com.kevin.multiapiapp.domain.model.unsplash.UnsplashDomain
import com.kevin.multiapiapp.domain.repository.UnsplashRepository
import com.kevin.multiapiapp.domain.usecase.unsplash.SearchUnsplashUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUnsplashUseCaseImpl @Inject constructor (
    private val unsplashRepository: UnsplashRepository
) : SearchUnsplashUseCase {

    /**
     * Executes the use case to search photos on Unsplash.
     *
     * @param query The search query string.
     * @param page The page number for paginated results.
     * @return A Flow emitting the search results as a domain model.
     */
    override suspend operator fun invoke(
        query: String, page: Int
    ): Flow<UnsplashDomain> = unsplashRepository.searchPhotos(
        query, page
    )
}