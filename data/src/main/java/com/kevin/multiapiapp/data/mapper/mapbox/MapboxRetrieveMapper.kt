package com.kevin.multiapiapp.data.mapper.mapbox

import com.kevin.multiapiapp.data.model.mapbox.CoordinatesDTO
import com.kevin.multiapiapp.data.model.mapbox.FeatureDTO
import com.kevin.multiapiapp.data.model.mapbox.MapBoxContextDTO
import com.kevin.multiapiapp.data.model.mapbox.MapboxRetrieveDTO
import com.kevin.multiapiapp.domain.model.mapbox.CoordinatesDomain
import com.kevin.multiapiapp.domain.model.mapbox.MapBoxContextDomain
import com.kevin.multiapiapp.domain.model.mapbox.MapboxRetrieveDomain
import com.kevin.multiapiapp.domain.model.mapbox.PropertiesDomain

object MapboxRetrieveMapper {

    // Map MapboxRetrieveLocationResponse to MapboxRetrieveDomain
    fun mapToDomain(response: MapboxRetrieveDTO): MapboxRetrieveDomain {
        return MapboxRetrieveDomain(
            properties = response.features.map { it.toDomain() }
        )
    }

    // Extension function to map Feature to PropertiesDomain
    private fun FeatureDTO.toDomain(): PropertiesDomain {
        return PropertiesDomain(
            context = this.properties.context.toDomain(),
            full_address = this.properties.full_address,
            coordinates = this.properties.coordinates.toDomain()
        )
    }

    // Extension function to map MapBoxContext to MapBoxContextDomain
    private fun MapBoxContextDTO.toDomain(): MapBoxContextDomain {
        return MapBoxContextDomain(
            country = this.country?.name,
            place = this.place?.name,
            street = this.street?.name
        )
    }

    // Extension function to map Coordinates to CoordinatesDomain
    private fun CoordinatesDTO.toDomain(): CoordinatesDomain {
        return CoordinatesDomain(
            longitude = this.longitude,
            latitude = this.latitude
        )
    }
}
