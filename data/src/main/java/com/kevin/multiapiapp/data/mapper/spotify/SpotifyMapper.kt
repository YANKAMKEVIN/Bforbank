package com.kevin.multiapiapp.data.mapper.spotify

import com.kevin.multiapiapp.data.model.spotify.AlbumDTO
import com.kevin.multiapiapp.data.model.spotify.ArtistDTO
import com.kevin.multiapiapp.data.model.spotify.ImageDTO
import com.kevin.multiapiapp.data.model.spotify.SpotifySearchDTO
import com.kevin.multiapiapp.data.model.spotify.TrackDTO
import com.kevin.multiapiapp.data.model.spotify.TracksDTO
import com.kevin.multiapiapp.domain.model.spotify.AlbumDomain
import com.kevin.multiapiapp.domain.model.spotify.ArtistDomain
import com.kevin.multiapiapp.domain.model.spotify.ImageDomain
import com.kevin.multiapiapp.domain.model.spotify.SpotifySearchDomain
import com.kevin.multiapiapp.domain.model.spotify.TrackDomain
import com.kevin.multiapiapp.domain.model.spotify.TracksDomain

object SpotifyMapper {

    // Map SpotifySearchDTO to SpotifySearchDomain
    fun mapToDomain(dto: SpotifySearchDTO): SpotifySearchDomain {
        return SpotifySearchDomain(
            tracks = dto.tracks.toDomain()
        )
    }

    // Extension function to map TracksDTO to TracksDomain
    private fun TracksDTO.toDomain(): TracksDomain {
        return TracksDomain(
            items = this.items.map { it.toDomain() }
        )
    }

    // Extension function to map TrackDTO to TrackDomain
    private fun TrackDTO.toDomain(): TrackDomain {
        return TrackDomain(
            name = this.name,
            artists = this.artists.map { it.toDomain() },
            album = this.album.toDomain()
        )
    }

    // Extension function to map ArtistDTO to ArtistDomain
    private fun ArtistDTO.toDomain(): ArtistDomain {
        return ArtistDomain(
            name = this.name
        )
    }

    // Extension function to map AlbumDTO to AlbumDomain
    private fun AlbumDTO.toDomain(): AlbumDomain {
        return AlbumDomain(
            images = this.images.map { it.toDomain() }
        )
    }

    // Extension function to map ImageDTO to ImageDomain
    private fun ImageDTO.toDomain(): ImageDomain {
        return ImageDomain(
            url = this.url
        )
    }
}