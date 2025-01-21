package com.kevin.multiapiapp

import com.kevin.multiapiapp.domain.model.mapbox.MapboxSearchDomain
import com.kevin.multiapiapp.domain.model.mapbox.SuggestionDomain
import com.kevin.multiapiapp.domain.repository.MapboxRepository
import com.kevin.multiapiapp.domain.usecase.mapbox.impl.SearchLocationUseCaseImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class SearchLocationUseCaseImplTest {

    private lateinit var searchLocationUseCase: SearchLocationUseCaseImpl
    private val mapboxRepository: MapboxRepository = mockk()

    @Before
    fun setup() {
        searchLocationUseCase = SearchLocationUseCaseImpl(mapboxRepository)
    }

    @Test
    fun `should search location successfully`() = runTest {
        // Arrange
        val searchResult = MapboxSearchDomain(
            suggestions = listOf(
                SuggestionDomain(
                    name = "Paris",
                    mapbox_id = "1",
                    address = "Some address in Paris",
                    full_address = "Full address in Paris"
                ),
                SuggestionDomain(
                    name = "Lyon",
                    mapbox_id = "2",
                    address = "Some address in Lyon",
                    full_address = "Full address in Lyon"
                )
            )
        )

        coEvery {
            mapboxRepository.searchLocation(
                query = "Paris",
                sessionToken = "sessionToken"
            )
        } returns flow {
            emit(searchResult)
        }

        // Act & Assert
        searchLocationUseCase.invoke(query = "Paris", sessionToken = "sessionToken")
            .collect { emittedValue ->
                assertEquals(searchResult, emittedValue)
            }

        coVerify { mapboxRepository.searchLocation(query = "Paris", sessionToken = "sessionToken") }
    }

    @Test
    fun `should handle empty search result`() = runTest {
        // Arrange
        val emptySearchResult = MapboxSearchDomain(
            suggestions = emptyList()
        )

        coEvery {
            mapboxRepository.searchLocation(
                query = "UnknownLocation",
                sessionToken = "sessionToken"
            )
        } returns flow {
            emit(emptySearchResult)
        }

        // Act & Assert
        searchLocationUseCase.invoke(query = "UnknownLocation", sessionToken = "sessionToken")
            .collect { emittedValue ->
                assertEquals(emptySearchResult, emittedValue)
            }

        coVerify {
            mapboxRepository.searchLocation(
                query = "UnknownLocation",
                sessionToken = "sessionToken"
            )
        }
    }
}
