package com.kevin.multiapiapp

import com.kevin.multiapiapp.domain.model.spotify.AlbumDomain
import com.kevin.multiapiapp.domain.model.spotify.ArtistDomain
import com.kevin.multiapiapp.domain.model.spotify.ImageDomain
import com.kevin.multiapiapp.domain.model.spotify.SpotifySearchDomain
import com.kevin.multiapiapp.domain.model.spotify.TrackDomain
import com.kevin.multiapiapp.domain.model.spotify.TracksDomain
import com.kevin.multiapiapp.domain.repository.SpotifyRepository
import com.kevin.multiapiapp.domain.usecase.spotify.impl.SearchTrackUseCaseImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class SearchTrackUseCaseImplTest {

    private lateinit var searchTracksUseCase: SearchTrackUseCaseImpl
    private val spotifyRepository: SpotifyRepository = mockk()

    @Before
    fun setup() {
        searchTracksUseCase = SearchTrackUseCaseImpl(spotifyRepository)
    }

    @Test
    fun `should return search results successfully`() = runTest {
        // Arrange
        val track = TrackDomain(
            name = "Shape of You",
            artists = listOf(ArtistDomain(name = "Ed Sheeran")),
            album = AlbumDomain(images = listOf(ImageDomain(url = "http://example.com/album.jpg")))
        )

        val spotifySearchDomain = SpotifySearchDomain(tracks = TracksDomain(items = listOf(track)))

        coEvery {
            spotifyRepository.searchTracks(
                query = "Shape of You",
                offset = 0
            )
        } returns flow {
            emit(spotifySearchDomain)
        }

        // Act & Assert
        searchTracksUseCase.invoke(query = "Shape of You", offset = 0).collect { result ->
            assertEquals(spotifySearchDomain, result)
        }

        // Verify the repository call
        coVerify { spotifyRepository.searchTracks(query = "Shape of You", offset = 0) }
    }

    @Test
    fun `should handle empty search results`() = runTest {
        // Arrange
        val emptySearchDomain = SpotifySearchDomain(tracks = TracksDomain(items = emptyList()))

        coEvery {
            spotifyRepository.searchTracks(
                query = "Unknown Song",
                offset = 0
            )
        } returns flow {
            emit(emptySearchDomain)
        }

        // Act & Assert
        searchTracksUseCase.invoke(query = "Unknown Song", offset = 0).collect { result ->
            assertEquals(emptySearchDomain, result)
        }

        // Verify the repository call
        coVerify { spotifyRepository.searchTracks(query = "Unknown Song", offset = 0) }
    }
}
