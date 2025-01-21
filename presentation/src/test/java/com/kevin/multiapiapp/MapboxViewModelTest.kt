package com.kevin.multiapiapp

import com.kevin.multiapiapp.domain.model.mapbox.MapboxRetrieveDomain
import com.kevin.multiapiapp.domain.model.mapbox.MapboxSearchDomain
import com.kevin.multiapiapp.domain.model.mapbox.SuggestionDomain
import com.kevin.multiapiapp.domain.usecase.mapbox.RetrieveLocationDetailsUseCase
import com.kevin.multiapiapp.domain.usecase.mapbox.SearchLocationUseCase
import com.kevin.multiapiapp.presentation.ui.mapbox.MapboxViewModel
import com.kevin.multiapiapp.testing.MainDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

@ExperimentalCoroutinesApi
class MapboxViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var searchLocationUseCase: SearchLocationUseCase

    @MockK
    private lateinit var retrieveLocationDetailsUseCase: RetrieveLocationDetailsUseCase

    private lateinit var mapboxViewModel: MapboxViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        mapboxViewModel = MapboxViewModel(searchLocationUseCase, retrieveLocationDetailsUseCase)
    }

    @Test
    fun `should search locations successfully`() = runTest {
        // Given
        val suggestions = listOf(
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
        val searchResult = MapboxSearchDomain(suggestions)

        coEvery { searchLocationUseCase("Paris", any()) } returns flowOf(
            searchResult
        )

        // When
        mapboxViewModel.search("Paris")

        // Then
        assertEquals(suggestions, mapboxViewModel.state.value.suggestions)
        assertEquals(false, mapboxViewModel.state.value.isLoading)
    }

    @Test
    fun `should handle search failure properly`() = runTest {
        // Given
        coEvery {
            searchLocationUseCase(
                "Paris",
                any()
            )
        } returns flow { throw Exception("Network error") }

        // When
        mapboxViewModel.search("Paris")

        // Then
        assertEquals("Network error", mapboxViewModel.state.value.errorMessage)
        assertEquals(false, mapboxViewModel.state.value.isLoading)
    }

    @Test
    fun `should retrieve location details successfully after suggestion is selected`() = runTest {
        // Given
        val suggestion = SuggestionDomain(
            name = "Paris",
            mapbox_id = "1",
            address = "Address 1",
            full_address = "full address 1"
        )
        val locationDetails = MapboxRetrieveDomain(properties = listOf())

        coEvery { retrieveLocationDetailsUseCase("1", any()) } returns flowOf(locationDetails)

        // When
        mapboxViewModel.onSuggestionSelected(suggestion)

        // Then
        assertEquals(suggestion, mapboxViewModel.state.value.selectedSuggestion)
        assertEquals(locationDetails, mapboxViewModel.state.value.locationDetails)
        assertEquals(false, mapboxViewModel.state.value.isLoading)
    }

    @Test
    fun `should handle location details retrieval failure properly`() = runTest {
        // Given
        val suggestion = SuggestionDomain(
            name = "Paris",
            mapbox_id = "1",
            address = "Address 1",
            full_address = "full address 1"
        )

        coEvery {
            retrieveLocationDetailsUseCase(
                "1",
                any()
            )
        } returns flow { throw Exception("Failed to retrieve details") }

        // When
        mapboxViewModel.onSuggestionSelected(suggestion)

        // Then
        assertEquals("Failed to retrieve details", mapboxViewModel.state.value.errorMessage)
        assertEquals(false, mapboxViewModel.state.value.isLoading)
    }

    @Test
    fun `should update query correctly when onQueryChanged is called`() {
        // Given
        val query = "New Query"

        // When
        mapboxViewModel.onQueryChanged(query)

        // Then
        assertEquals(query, mapboxViewModel.state.value.query)
        assertNull(mapboxViewModel.state.value.selectedSuggestion)
        assertNull(mapboxViewModel.state.value.errorMessage)
    }
}
