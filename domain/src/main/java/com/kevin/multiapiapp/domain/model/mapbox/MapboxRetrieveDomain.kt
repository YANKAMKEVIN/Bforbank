package com.kevin.multiapiapp.domain.model.mapbox


data class MapboxRetrieveDomain(
    val properties: List<PropertiesDomain>,
)

data class PropertiesDomain(
    val context: MapBoxContextDomain,
    val full_address: String? = null,
    val coordinates: CoordinatesDomain,
)

data class MapBoxContextDomain(
    val country: String? = null,
    val place: String? = null,
    val street: String? = null,
)

data class CoordinatesDomain(
    val longitude: Double,
    val latitude: Double,
)