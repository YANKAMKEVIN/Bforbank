package com.kevin.multiapiapp.data.model.spotify

data class SpotifySearchDTO(
    val tracks: TracksDTO
)

data class TracksDTO(
    val items: List<TrackDTO>
)

data class TrackDTO(
    val name: String,
    val artists: List<ArtistDTO>,
    val album: AlbumDTO
)

data class ArtistDTO(
    val name: String
)

data class AlbumDTO(
    val images: List<ImageDTO>
)

data class ImageDTO(
    val url: String
)
