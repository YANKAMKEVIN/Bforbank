package com.kevin.multiapiapp

import com.kevin.multiapiapp.domain.model.unsplash.PhotoDomain
import com.kevin.multiapiapp.domain.model.unsplash.UnsplashDomain
import com.kevin.multiapiapp.domain.model.unsplash.UrlsDomain
import com.kevin.multiapiapp.domain.repository.UnsplashRepository
import com.kevin.multiapiapp.domain.usecase.unsplash.impl.SearchUnsplashUseCaseImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class SearchUnsplashUseCaseImplTest {

    private lateinit var searchUnsplashUseCase: SearchUnsplashUseCaseImpl
    private val unsplashRepository: UnsplashRepository = mockk()

    @Before
    fun setup() {
        searchUnsplashUseCase = SearchUnsplashUseCaseImpl(unsplashRepository)
    }

    @Test
    fun `should search photos successfully`() = runTest {
        // Arrange
        val query = "nature"
        val page = 1
        val expectedResponse = UnsplashDomain(
            results = listOf(
                PhotoDomain(
                    id = "photo1",
                    urls = UrlsDomain(
                        regular = "https://example.com/photo1.jpg",
                        small = "https://example.com/photo1_small.jpg"
                    ),
                    description = "Beautiful nature photo"
                )
            )
        )

        coEvery { unsplashRepository.searchPhotos(query, page) } returns flow {
            emit(expectedResponse)
        }

        // Act
        val result = searchUnsplashUseCase.invoke(query, page)

        // Assert
        result.collect { response ->
            assertEquals(expectedResponse, response)
        }

        coVerify { unsplashRepository.searchPhotos(query, page) }
    }

    @Test
    fun `should handle empty results`() = runTest {
        // Arrange
        val query = "nonexistent"
        val page = 1
        val emptyResponse = UnsplashDomain(results = emptyList())

        coEvery { unsplashRepository.searchPhotos(query, page) } returns flow {
            emit(emptyResponse)
        }

        // Act
        val result = searchUnsplashUseCase.invoke(query, page)

        // Assert
        result.collect { response ->
            assertEquals(emptyResponse, response)
        }

        coVerify { unsplashRepository.searchPhotos(query, page) }
    }
}
