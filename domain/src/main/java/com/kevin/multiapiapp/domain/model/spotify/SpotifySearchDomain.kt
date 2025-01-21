package com.kevin.multiapiapp.domain.model.spotify


data class SpotifySearchDomain(
    val tracks: TracksDomain
)

data class TracksDomain(
    val items: List<TrackDomain>
)

data class TrackDomain(
    val name: String,
    val artists: List<ArtistDomain>,
    val album: AlbumDomain
)

data class ArtistDomain(
    val name: String
)

data class AlbumDomain(
    val images: List<ImageDomain>
)

data class ImageDomain(
    val url: String
)