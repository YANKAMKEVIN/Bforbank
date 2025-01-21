package com.kevin.multiapiapp.data.mapper.unsplash

import com.kevin.multiapiapp.data.model.unsplash.PhotoDTO
import com.kevin.multiapiapp.data.model.unsplash.UnsplashResponseDTO
import com.kevin.multiapiapp.data.model.unsplash.UrlsDTO
import com.kevin.multiapiapp.domain.model.unsplash.PhotoDomain
import com.kevin.multiapiapp.domain.model.unsplash.UnsplashDomain
import com.kevin.multiapiapp.domain.model.unsplash.UrlsDomain

object UnsplashMapper {

    // Map UnsplashResponseDTO to UnsplashDomain
    fun mapToDomain(dto: UnsplashResponseDTO): UnsplashDomain {
        return UnsplashDomain(
            results = dto.results.map { it.toDomain() }
        )
    }

    // Extension function to map PhotoDTO to PhotoDomain
    private fun PhotoDTO.toDomain(): PhotoDomain {
        return PhotoDomain(
            id = this.id,
            urls = this.urls.toDomain(),
            description = this.description
        )
    }

    // Extension function to map UrlsDTO to UrlsDomain
    private fun UrlsDTO.toDomain(): UrlsDomain {
        return UrlsDomain(
            regular = this.regular,
            small = this.small
        )
    }
}