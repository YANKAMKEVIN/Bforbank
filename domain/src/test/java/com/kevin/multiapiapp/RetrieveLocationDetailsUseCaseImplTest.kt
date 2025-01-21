package com.kevin.multiapiapp

import com.kevin.multiapiapp.domain.model.mapbox.CoordinatesDomain
import com.kevin.multiapiapp.domain.model.mapbox.MapBoxContextDomain
import com.kevin.multiapiapp.domain.model.mapbox.MapboxRetrieveDomain
import com.kevin.multiapiapp.domain.model.mapbox.PropertiesDomain
import com.kevin.multiapiapp.domain.repository.MapboxRepository
import com.kevin.multiapiapp.domain.usecase.mapbox.impl.RetrieveLocationDetailsUseCaseImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class RetrieveLocationDetailsUseCaseImplTest {

    private lateinit var retrieveLocationDetailsUseCase: RetrieveLocationDetailsUseCaseImpl
    private val mapboxRepository: MapboxRepository = mockk()

    @Before
    fun setup() {
        retrieveLocationDetailsUseCase = RetrieveLocationDetailsUseCaseImpl(mapboxRepository)
    }

    @Test
    fun `should fetch location details successfully`() = runTest {
        // Arrange
        val mapboxRetrieveDomain = MapboxRetrieveDomain(
            properties = listOf(
                PropertiesDomain(
                    context = MapBoxContextDomain(country = "France", place = "Paris", street = "Rue de Rivoli"),
                    full_address = "Rue de Rivoli, Paris, France",
                    coordinates = CoordinatesDomain(longitude = 2.3522, latitude = 48.8566)
                )
            )
        )

        coEvery {
            mapboxRepository.retrieveLocation(mapBoxId = "123", sessionToken = "sessionToken")
        } returns flow {
            emit(mapboxRetrieveDomain)
        }

        // Act & Assert
        retrieveLocationDetailsUseCase.invoke("123", "sessionToken").collect { emittedValue ->
            assertEquals(mapboxRetrieveDomain, emittedValue)
        }

        coVerify { mapboxRepository.retrieveLocation("123", "sessionToken") }
    }

    @Test
    fun `should handle empty location details`() = runTest {
        // Arrange
        val emptyMapboxRetrieveDomain = MapboxRetrieveDomain(properties = emptyList())

        coEvery {
            mapboxRepository.retrieveLocation(mapBoxId = "123", sessionToken = "sessionToken")
        } returns flow {
            emit(emptyMapboxRetrieveDomain)
        }

        // Act & Assert
        retrieveLocationDetailsUseCase.invoke("123", "sessionToken").collect { emittedValue ->
            assertEquals(emptyMapboxRetrieveDomain, emittedValue)
        }

        coVerify { mapboxRepository.retrieveLocation("123", "sessionToken") }
    }
}
