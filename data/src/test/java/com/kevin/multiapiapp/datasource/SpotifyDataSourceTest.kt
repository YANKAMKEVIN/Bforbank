package com.kevin.multiapiapp.datasource

import com.kevin.multiapiapp.common.network.NetworkError
import com.kevin.multiapiapp.common.network.NetworkResponse
import com.kevin.multiapiapp.data.api.spotify.SpotifyApi
import com.kevin.multiapiapp.data.datasource.spotify.impl.SpotifyDataSourceImpl
import com.kevin.multiapiapp.data.model.spotify.AlbumDTO
import com.kevin.multiapiapp.data.model.spotify.ArtistDTO
import com.kevin.multiapiapp.data.model.spotify.ImageDTO
import com.kevin.multiapiapp.data.model.spotify.SpotifySearchDTO
import com.kevin.multiapiapp.data.model.spotify.TrackDTO
import com.kevin.multiapiapp.data.model.spotify.TracksDTO
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SpotifyDataSourceTest {

    private lateinit var spotifyApi: SpotifyApi
    private lateinit var spotifyDataSource: SpotifyDataSourceImpl

    @Before
    fun setup() {
        spotifyApi = mockk()
        spotifyDataSource = SpotifyDataSourceImpl(spotifyApi)
    }

    @Test
    fun `should return success response when searchTracks is called`() = runBlocking {
        // Arrange
        val query = "Beatles"
        val offset = 0
        val image1 = ImageDTO(url = "https://spotify.com/image1.jpg")
        val image2 = ImageDTO(url = "https://spotify.com/image2.jpg")
        val album = AlbumDTO(images = listOf(image1, image2))

        val artist1 = ArtistDTO(name = "Artist 1")
        val artist2 = ArtistDTO(name = "Artist 2")

        val track1 = TrackDTO(
            name = "Track 1",
            artists = listOf(artist1, artist2),
            album = album
        )
        val track2 = TrackDTO(
            name = "Track 2",
            artists = listOf(artist1),
            album = album
        )

        val tracks = TracksDTO(items = listOf(track1, track2))
        val mockResponse = SpotifySearchDTO(tracks = tracks)

        coEvery { spotifyApi.searchTracks(query, "track", 10, offset) } returns mockResponse

        // Act
        val result = spotifyDataSource.searchTracks(query, offset)

        // Assert
        assertTrue(result is NetworkResponse.Success)
        assertEquals(mockResponse, result.data)
    }

    @Test
    fun `should return failure response when searchTracks fails with exception`() = runBlocking {
        // Arrange
        val query = "Beatles"
        val offset = 0

        val errorMessage = "API call failed"
        coEvery { spotifyApi.searchTracks(query, "track", 10, offset) } throws Exception(
            errorMessage
        )

        // Act
        val result = spotifyDataSource.searchTracks(query, offset)

        // Assert
        assertTrue(result is NetworkResponse.Failure)
        val error = result.error
        assertTrue(error is NetworkError.Unknown)
        assertEquals(errorMessage, error.message)
    }

    @Test
    fun `should return failure response when searchTracks returns an unknown error`() =
        runBlocking {
            // Arrange
            val query = "Beatles"
            val offset = 0

            val errorMessage = "Unknown error"
            coEvery { spotifyApi.searchTracks(query, "track", 10, offset) } throws Exception(
                errorMessage
            )

            // Act
            val result = spotifyDataSource.searchTracks(query, offset)

            // Assert
            assertTrue(result is NetworkResponse.Failure)
            val error = result.error
            assertTrue(error is NetworkError.Unknown)
            assertEquals(-2, error.code)
            assertEquals(errorMessage, error.message)
        }
}
