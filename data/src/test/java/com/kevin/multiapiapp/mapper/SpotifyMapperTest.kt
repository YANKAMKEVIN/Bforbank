package com.kevin.multiapiapp.mapper

import com.kevin.multiapiapp.data.model.spotify.*
import com.kevin.multiapiapp.data.mapper.spotify.SpotifyMapper
import com.kevin.multiapiapp.domain.model.spotify.SpotifySearchDomain
import org.junit.Assert.assertEquals
import org.junit.Test

class SpotifyMapperTest {

    @Test
    fun `mapToDomain should correctly map SpotifySearchDTO to SpotifySearchDomain`() {
        // Arrange: Create SpotifySearchDTO with test data
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
        val spotifySearchDTO = SpotifySearchDTO(tracks = tracks)

        // Act: Map the DTO to a Domain object
        val result: SpotifySearchDomain = SpotifyMapper.mapToDomain(spotifySearchDTO)

        // Assert: Verify the mapping results
        val domainTracks = result.tracks.items
        assertEquals(2, domainTracks.size)

        // Verify Track 1
        val domainTrack1 = domainTracks[0]
        assertEquals("Track 1", domainTrack1.name)
        assertEquals(2, domainTrack1.artists.size)
        assertEquals("Artist 1", domainTrack1.artists[0].name)
        assertEquals("Artist 2", domainTrack1.artists[1].name)
        assertEquals(2, domainTrack1.album.images.size)
        assertEquals("https://spotify.com/image1.jpg", domainTrack1.album.images[0].url)
        assertEquals("https://spotify.com/image2.jpg", domainTrack1.album.images[1].url)

        // Verify Track 2
        val domainTrack2 = domainTracks[1]
        assertEquals("Track 2", domainTrack2.name)
        assertEquals(1, domainTrack2.artists.size)
        assertEquals("Artist 1", domainTrack2.artists[0].name)
        assertEquals(2, domainTrack2.album.images.size)
        assertEquals("https://spotify.com/image1.jpg", domainTrack2.album.images[0].url)
        assertEquals("https://spotify.com/image2.jpg", domainTrack2.album.images[1].url)
    }
}
