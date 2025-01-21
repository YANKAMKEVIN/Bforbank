package com.kevin.multiapiapp.mapper

import com.kevin.multiapiapp.data.mapper.mapbox.MapboxRetrieveMapper
import com.kevin.multiapiapp.data.model.mapbox.CoordinatesDTO
import com.kevin.multiapiapp.data.model.mapbox.Country
import com.kevin.multiapiapp.data.model.mapbox.FeatureDTO
import com.kevin.multiapiapp.data.model.mapbox.GeometryDTO
import com.kevin.multiapiapp.data.model.mapbox.MapBoxContextDTO
import com.kevin.multiapiapp.data.model.mapbox.MapboxRetrieveDTO
import com.kevin.multiapiapp.data.model.mapbox.Place
import com.kevin.multiapiapp.data.model.mapbox.PropertiesDTO
import com.kevin.multiapiapp.data.model.mapbox.Street
import com.kevin.multiapiapp.domain.model.mapbox.CoordinatesDomain
import com.kevin.multiapiapp.domain.model.mapbox.MapBoxContextDomain
import com.kevin.multiapiapp.domain.model.mapbox.MapboxRetrieveDomain
import com.kevin.multiapiapp.domain.model.mapbox.PropertiesDomain
import org.junit.Assert.assertEquals
import org.junit.Test

class MapboxRetrieveMapperTest {

    @Test
    fun `mapToDomain maps MapboxRetrieveDTO to MapboxRetrieveDomain`() {
        // Arrange
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
        val responseDTO = MapboxRetrieveDTO(
            features = listOf(featureDTO),
            attribution = "attribution",
            type = "type"
        )

        // Act
        val result = MapboxRetrieveMapper.mapToDomain(responseDTO)

        // Assert
        val expectedCoordinatesDomain = CoordinatesDomain(longitude = 2.3488, latitude = 48.8534)
        val expectedMapBoxContextDomain = MapBoxContextDomain(
            country = "France",
            place = "Paris",
            street = "Rue de Rivoli"
        )
        val expectedPropertiesDomain = PropertiesDomain(
            context = expectedMapBoxContextDomain,
            full_address = "Rue de Rivoli, Paris, France",
            coordinates = expectedCoordinatesDomain
        )
        val expectedDomain = MapboxRetrieveDomain(properties = listOf(expectedPropertiesDomain))

        assertEquals(expectedDomain, result)
    }
}
