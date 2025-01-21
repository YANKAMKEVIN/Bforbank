package com.kevin.multiapiapp

import com.kevin.multiapiapp.domain.model.unsplash.PhotoDomain
import com.kevin.multiapiapp.domain.model.unsplash.UnsplashDomain
import com.kevin.multiapiapp.domain.model.unsplash.UrlsDomain
import com.kevin.multiapiapp.domain.usecase.unsplash.SearchUnsplashUseCase
import com.kevin.multiapiapp.presentation.ui.unsplash.UnsplashViewModel
import com.kevin.multiapiapp.testing.MainDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class UnsplashViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var searchUnsplashUseCase: SearchUnsplashUseCase

    private lateinit var unsplashViewModel: UnsplashViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        unsplashViewModel = UnsplashViewModel(searchUnsplashUseCase)
    }

    @Test
    fun `should search photos successfully`() = runTest {
        // Given
        val mockResponse = UnsplashDomain(
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

        coEvery { searchUnsplashUseCase(any(), any()) } returns flowOf(mockResponse)
        unsplashViewModel.onQueryChanged("mockQuery")

        // When
        unsplashViewModel.searchPhotos()

        // Then
        assertEquals(mockResponse.results, unsplashViewModel.state.value.photos)
        assertEquals(false, unsplashViewModel.state.value.isLoading)
    }

    @Test
    fun `should handle search failure properly`() = runTest {
        // Given
        coEvery { searchUnsplashUseCase("test", 1) } returns flowOf()

        // When
        unsplashViewModel.searchPhotos()

        // Then
        assertEquals(false, unsplashViewModel.state.value.isLoading)
    }

    @Test
    fun `should reset state when query changes`() {
        // Given
        val query = "New Query"

        // When
        unsplashViewModel.onQueryChanged(query)

        // Then
        assertEquals(query, unsplashViewModel.state.value.query)
        assertEquals(emptyList(), unsplashViewModel.state.value.photos)
        assertEquals(1, unsplashViewModel.state.value.page)
        assertEquals(false, unsplashViewModel.state.value.isLoading)
    }

    @Test
    fun `should not search if query is blank`() = runTest {
        // Given
        val blankQuery = ""

        // When: Call searchPhotos with a blank query
        unsplashViewModel.onQueryChanged(blankQuery)
        unsplashViewModel.searchPhotos()

        // Then: No search should be made, and state should remain unchanged
        assertEquals(emptyList(), unsplashViewModel.state.value.photos)
        assertEquals(1, unsplashViewModel.state.value.page)
        assertEquals(false, unsplashViewModel.state.value.isLoading)
    }
}
