package com.kevin.multiapiapp.mapper

import com.kevin.multiapiapp.data.model.unsplash.*
import com.kevin.multiapiapp.data.mapper.unsplash.UnsplashMapper
import com.kevin.multiapiapp.domain.model.unsplash.UnsplashDomain
import org.junit.Assert.assertEquals
import org.junit.Test

class UnsplashMapperTest {

    @Test
    fun `mapToDomain should correctly map UnsplashResponseDTO to UnsplashDomain`() {
        // Arrange: Create an UnsplashResponseDTO with test data
        val photo1 = PhotoDTO(
            id = "photo123",
            urls = UrlsDTO(
                regular = "https://unsplash.com/photo1_regular.jpg",
                small = "https://unsplash.com/photo1_small.jpg"
            ),
            description = "A beautiful sunset over the mountains"
        )

        val photo2 = PhotoDTO(
            id = "photo456",
            urls = UrlsDTO(
                regular = "https://unsplash.com/photo2_regular.jpg",
                small = "https://unsplash.com/photo2_small.jpg"
            ),
            description = "A serene beach at sunrise"
        )

        val unsplashResponseDTO = UnsplashResponseDTO(
            results = listOf(photo1, photo2),
        )

        // Act: Map the DTO to a Domain object
        val result: UnsplashDomain = UnsplashMapper.mapToDomain(unsplashResponseDTO)

        // Assert: Verify the mapping results
        assertEquals(2, result.results.size)

        val domainPhoto1 = result.results[0]
        assertEquals("photo123", domainPhoto1.id)
        assertEquals("https://unsplash.com/photo1_regular.jpg", domainPhoto1.urls.regular)
        assertEquals("https://unsplash.com/photo1_small.jpg", domainPhoto1.urls.small)
        assertEquals("A beautiful sunset over the mountains", domainPhoto1.description)

        val domainPhoto2 = result.results[1]
        assertEquals("photo456", domainPhoto2.id)
        assertEquals("https://unsplash.com/photo2_regular.jpg", domainPhoto2.urls.regular)
        assertEquals("https://unsplash.com/photo2_small.jpg", domainPhoto2.urls.small)
        assertEquals("A serene beach at sunrise", domainPhoto2.description)
    }
}
