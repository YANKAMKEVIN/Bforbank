package com.kevin.multiapiapp.datasource

import com.kevin.multiapiapp.common.network.NetworkError
import com.kevin.multiapiapp.common.network.NetworkResponse
import com.kevin.multiapiapp.data.api.unsplash.UnsplashApi
import com.kevin.multiapiapp.data.datasource.unsplash.impl.UnsplashDataSourceImpl
import com.kevin.multiapiapp.data.model.unsplash.PhotoDTO
import com.kevin.multiapiapp.data.model.unsplash.UnsplashResponseDTO
import com.kevin.multiapiapp.data.model.unsplash.UrlsDTO
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class UnsplashDataSourceImplTest {

    private lateinit var unsplashApi: UnsplashApi
    private lateinit var unsplashDataSource: UnsplashDataSourceImpl

    @Before
    fun setup() {
        unsplashApi = mockk()
        unsplashDataSource = UnsplashDataSourceImpl(unsplashApi)
    }

    @Test
    fun `should return success response when API call is successful`() = runBlocking {
        // Arrange
        val query = "cats"
        val page = 1
        val clientId = "clientId"
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
        val mockResponse = UnsplashResponseDTO(
            results = listOf(photo1, photo2),
        )

        // Mock de l'appel API
        coEvery {
            unsplashApi.searchPhotos(
                query = query,
                page = page,
                clientId = clientId
            )
        } returns mockResponse

        // Act
        val result = unsplashDataSource.searchPhotos(query, page, clientId)

        // Assert
        assertTrue(result is NetworkResponse.Success)
        assertEquals(
            mockResponse,
            (result as NetworkResponse.Success).data
        )
    }


    @Test
    fun `should return failure response when searchPhotos fails with exception`() = runBlocking {
        // Arrange
        val query = "cats"
        val page = 1
        val clientId = "clientId"

        coEvery {
            unsplashApi.searchPhotos(
                query,
                page,
                20,
                clientId
            )
        } throws Exception("API call failed")

        // Act
        val result = unsplashDataSource.searchPhotos(query, page, clientId)

        // Assert
        assertTrue(result is NetworkResponse.Failure)
        val error = (result as NetworkResponse.Failure).error
        assertTrue(error is NetworkError.Unknown)
        assertEquals("API call failed", error.message)
    }
}
