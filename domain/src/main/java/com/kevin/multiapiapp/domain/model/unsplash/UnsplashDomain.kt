package com.kevin.multiapiapp.domain.model.unsplash

data class UnsplashDomain(
    val results: List<PhotoDomain>
)

data class PhotoDomain(
    val id: String,
    val urls: UrlsDomain,
    val description: String?
)

data class UrlsDomain(
    val regular: String,
    val small: String
)