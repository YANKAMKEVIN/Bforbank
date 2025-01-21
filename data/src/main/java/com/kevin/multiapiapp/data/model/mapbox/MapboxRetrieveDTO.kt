package com.kevin.multiapiapp.data.model.mapbox

data class MapboxRetrieveDTO(
    val type: String,
    val features: List<FeatureDTO>,
    val attribution: String,
)

data class FeatureDTO(
    val type: String,
    val geometry: GeometryDTO,
    val properties: PropertiesDTO,
)

data class GeometryDTO(
    val coordinates: List<String>,
    val type: String
)

data class PropertiesDTO(
    val name: String,
    val name_preferred: String? = null,
    val mapbox_id: String,
    val feature_type: String,
    val address: String? = null,
    val full_address: String? = null,
    val place_formatted: String? = null,
    val context: MapBoxContextDTO,
    val coordinates: CoordinatesDTO,
    val bbox: BoundingBoxDTO? = null,
    val language: String? = null,
    val maki: String? = null,
    val poi_category: List<String>? = null,
    val poi_category_ids: List<String>? = null,
    val brand: List<String>? = null,
    val brand_id: List<String>? = null,
    val external_ids: ExternalIds? = null,
    val metadata: MMetadataDTO? = null,
)

data class MapBoxContextDTO(
    val country: Country? = null,
    val region: Region? = null,
    val postCode: Postcode? = null,
    val district: DistrictDTO? = null,
    val place: Place? = null,
    val locality: Locality? = null,
    val neighborhood: Neighborhood? = null,
    val address: Address? = null,
    val street: Street? = null,
)

data class DistrictDTO(val name: String)
data class Locality(val name: String)
data class Address(val name: String, val address_number: String, val street_name: String)

data class CoordinatesDTO(
    val longitude: Double,
    val latitude: Double,
    val accuracy: String? = null,
    val routable_points: List<RoutablePointsDTO>? = null,
)

data class MMetadataDTO(
    val metadata: String?
)

data class RoutablePointsDTO(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val note: String? = null
)

data class BoundingBoxDTO(
    val minimum_longitude: Int,
    val minimum_latitude: Int,
    val maximum_longitude: Int,
    val maximum_latitude: Int,
)