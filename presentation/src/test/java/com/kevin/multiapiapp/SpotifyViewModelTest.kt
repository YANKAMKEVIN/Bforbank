package com.kevin.multiapiapp

import com.kevin.multiapiapp.domain.model.spotify.AlbumDomain
import com.kevin.multiapiapp.domain.model.spotify.SpotifySearchDomain
import com.kevin.multiapiapp.domain.model.spotify.TrackDomain
import com.kevin.multiapiapp.domain.model.spotify.TracksDomain
import com.kevin.multiapiapp.domain.usecase.spotify.SearchTracksUseCase
import com.kevin.multiapiapp.presentation.ui.spotify.SpotifyViewModel
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
class SpotifyViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var searchTracksUseCase: SearchTracksUseCase

    private lateinit var spotifyViewModel: SpotifyViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        spotifyViewModel = SpotifyViewModel(searchTracksUseCase)
    }

    @Test
    fun `should search tracks successfully`() = runTest {
        // Given
        val tracks = listOf(
            TrackDomain(
                name = "Track 1",
                artists = listOf(),
                album = AlbumDomain(images = emptyList())
            ),
            TrackDomain(
                name = "Track 2",
                artists = listOf(),
                album = AlbumDomain(images = emptyList())
            )
        )
        val mockResponse = SpotifySearchDomain(tracks = TracksDomain(items = tracks))

        coEvery { searchTracksUseCase("test", 0) } returns flowOf(mockResponse)

        // When
        spotifyViewModel.searchTracks("test")

        // Then
        assertEquals(tracks, spotifyViewModel.state.value.tracks)
        assertEquals(false, spotifyViewModel.state.value.isLoading)
        assertEquals(false, spotifyViewModel.state.value.isLoadingMore)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should handle search failure properly`() = runTest {
        // Given
        coEvery { searchTracksUseCase("test", 0) } returns flow { throw Exception("Network error") }

        // When
        spotifyViewModel.searchTracks("test")

        // Then
        assertEquals("Network error", spotifyViewModel.state.value.errorMessage)
        assertEquals(false, spotifyViewModel.state.value.isLoading)
        assertEquals(false, spotifyViewModel.state.value.isLoadingMore)
    }

    @Test
    fun `should reset state when query changes`() {
        // Given
        val query = "New Query"

        // When
        spotifyViewModel.onQueryChanged(query)

        // Then
        assertEquals(query, spotifyViewModel.state.value.query)
        assertEquals(emptyList(), spotifyViewModel.state.value.tracks)
        assertNull(spotifyViewModel.state.value.errorMessage)
        assertEquals(false, spotifyViewModel.state.value.isLoading)
        assertEquals(false, spotifyViewModel.state.value.isLoadingMore)
    }
}
