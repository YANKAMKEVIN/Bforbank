package com.kevin.multiapiapp.data.model.unsplash

data class UnsplashResponseDTO(
    val results: List<PhotoDTO>
)

data class PhotoDTO(
    val id: String,
    val urls: UrlsDTO,
    val description: String?
)

data class UrlsDTO(
    val regular: String,
    val small: String
)