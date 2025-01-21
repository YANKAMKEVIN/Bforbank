package com.kevin.multiapiapp.datasource

import com.kevin.multiapiapp.common.network.NetworkError
import com.kevin.multiapiapp.common.network.NetworkResponse
import com.kevin.multiapiapp.data.api.mapbox.MapboxApi
import com.kevin.multiapiapp.data.datasource.mapbox.impl.MapboxDataSourceImpl
import com.kevin.multiapiapp.data.model.mapbox.ContextDTO
import com.kevin.multiapiapp.data.model.mapbox.CoordinatesDTO
import com.kevin.multiapiapp.data.model.mapbox.Country
import com.kevin.multiapiapp.data.model.mapbox.ExternalIds
import com.kevin.multiapiapp.data.model.mapbox.FeatureDTO
import com.kevin.multiapiapp.data.model.mapbox.GeometryDTO
import com.kevin.multiapiapp.data.model.mapbox.MapBoxContextDTO
import com.kevin.multiapiapp.data.model.mapbox.MapboxRetrieveDTO
import com.kevin.multiapiapp.data.model.mapbox.MapboxSearchDTO
import com.kevin.multiapiapp.data.model.mapbox.Neighborhood
import com.kevin.multiapiapp.data.model.mapbox.Place
import com.kevin.multiapiapp.data.model.mapbox.Postcode
import com.kevin.multiapiapp.data.model.mapbox.PropertiesDTO
import com.kevin.multiapiapp.data.model.mapbox.Region
import com.kevin.multiapiapp.data.model.mapbox.Street
import com.kevin.multiapiapp.data.model.mapbox.SuggestionDTO
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MapboxDataSourceTest {

    private lateinit var mapboxApi: MapboxApi
    private lateinit var mapboxDataSource: MapboxDataSourceImpl

    @Before
    fun setup() {
        mapboxApi = mockk()
        mapboxDataSource = MapboxDataSourceImpl(mapboxApi)
    }

    @Test
    fun `should return success response when searchLocation is called`() = runBlocking {
        // Arrange
        val query = "Paris"
        val sessionToken = "123456"
        val accessToken = "validAccessToken"
        val suggestion1 = SuggestionDTO(
            name = "Eiffel Tower",
            mapbox_id = "mbx123",
            feature_type = "landmark",
            address = "Champ de Mars",
            full_address = "Champ de Mars, 5 Avenue Anatole France, 75007 Paris, France",
            place_formatted = "Paris, France",
            context = ContextDTO(
                country = Country("France", "FR", "FRA"),
                region = Region("Île-de-France", "IDF", "Region Île-de-France"),
                postcode = Postcode("75007"),
                place = Place("Paris"),
                neighborhood = Neighborhood("7th Arrondissement"),
                street = Street("Avenue Anatole France")
            ),
            language = "en",
            maki = "marker",
            poi_category = listOf("tourism", "landmark"),
            external_ids = ExternalIds(
                safegraph = "safe123",
                foursquare = "4sq567"
            )
        )
        val suggestion2 = SuggestionDTO(
            name = "Louvre Museum",
            mapbox_id = "mbx456",
            feature_type = "museum",
            address = "Rue de Rivoli",
            full_address = "Rue de Rivoli, 75001 Paris, France",
            place_formatted = "Paris, France",
            context = ContextDTO(
                country = Country("France", "FR", "FRA"),
                region = Region("Île-de-France", "IDF", "Region Île-de-France"),
                postcode = Postcode("75001"),
                place = Place("Paris"),
                neighborhood = Neighborhood("1st Arrondissement"),
                street = Street("Rue de Rivoli")
            ),
            language = "en",
            maki = "museum",
            poi_category = listOf("art", "museum"),
            external_ids = ExternalIds(
                safegraph = "safe456",
                foursquare = "4sq890"
            )
        )

        val mockSearchResult = MapboxSearchDTO(
            suggestions = listOf(suggestion1, suggestion2),
            attribution = "Mapbox"
        )

        coEvery {
            mapboxApi.searchLocation(
                query = query,
                sessionToken = sessionToken,
                accessToken = accessToken
            )
        } returns mockSearchResult

        // Act
        val result = mapboxDataSource.searchLocation(query, sessionToken, accessToken)

        // Assert
        assertTrue(result is NetworkResponse.Success)
        assertEquals(mockSearchResult, result.data)
    }

    @Test
    fun `should return failure response when searchLocation fails with unknown error`() =
        runBlocking {
            // Arrange
            val query = "Paris"
            val sessionToken = "123456"
            val accessToken = "validAccessToken"
            val errorMessage = "Unknown error"

            coEvery {
                mapboxApi.searchLocation(
                    query = query,
                    sessionToken = sessionToken,
                    accessToken = accessToken
                )
            } throws
                    Exception(errorMessage)

            // Act
            val result = mapboxDataSource.searchLocation(query, sessionToken, accessToken)

            // Assert
            assertTrue(result is NetworkResponse.Failure)
            val error = result.error
            assertTrue(error is NetworkError.Unknown)
            assertEquals(-2, error.code)
            assertEquals(errorMessage, error.message)
        }

    @Test
    fun `should return success response when retrieveLocationDetails is called`() = runBlocking {
        // Arrange
        val mapBoxId = "1"
        val sessionToken = "123456"
        val accessToken = "validAccessToken"
        val coordinatesDTO = CoordinatesDTO(longitude = 2.3488, latitude = 48.8534)
        val mapBoxContextDTO = MapBoxContextDTO(
            country = Country(name = "France", country_code = "FR", country_code_alpha3 = "1"),
            place = Place(name = "Paris"),
            street = Street(name = "Rue de Rivoli")
        )
        val propertiesDTO = PropertiesDTO(
            context = mapBoxContextDTO,
            full_address = "Rue de Rivoli, Paris, France",
            coordinates = coordinatesDTO,
            name = "Name",
            mapbox_id = "mapbox_id",
            feature_type = "feature_type"
        )
        val geometryDTO = GeometryDTO(coordinates = emptyList(), type = "type")
        val featureDTO =
            FeatureDTO(properties = propertiesDTO, geometry = geometryDTO, type = "type")
        val mockRetrieveResult = MapboxRetrieveDTO(
            features = listOf(featureDTO),
            attribution = "attribution",
            type = "type"
        )
        coEvery {
            mapboxApi.retrieveLocationDetails(
                mapBoxId,
                sessionToken,
                accessToken
            )
        } returns mockRetrieveResult

        // Act
        val result = mapboxDataSource.retrieveLocationDetails(mapBoxId, sessionToken, accessToken)

        // Assert
        assertTrue(result is NetworkResponse.Success)
        assertEquals(mockRetrieveResult, result.data)
    }

    @Test
    fun `should return failure response when retrieveLocationDetails fails with unknown error`() =
        runBlocking {
            // Arrange
            val mapBoxId = "1"
            val sessionToken = "123456"
            val accessToken = "validAccessToken"
            val errorMessage = "Unknown error"

            coEvery {
                mapboxApi.retrieveLocationDetails(
                    mapBoxId,
                    sessionToken,
                    accessToken
                )
            } throws
                    Exception(errorMessage)

            // Act
            val result =
                mapboxDataSource.retrieveLocationDetails(mapBoxId, sessionToken, accessToken)

            // Assert
            assertTrue(result is NetworkResponse.Failure)
            val error = result.error
            assertTrue(error is NetworkError.Unknown)
            assertEquals(-2, error.code)
            assertEquals(errorMessage, error.message)
        }
}
