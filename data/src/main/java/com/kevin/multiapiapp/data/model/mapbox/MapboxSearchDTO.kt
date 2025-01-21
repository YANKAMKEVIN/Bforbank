package com.kevin.multiapiapp.data.model.mapbox

data class MapboxSearchDTO(
    val suggestions: List<SuggestionDTO>,
    val attribution: String
)

data class SuggestionDTO(
    val name: String,
    val mapbox_id: String,
    val feature_type: String,
    val address: String?,
    val full_address: String?,
    val place_formatted: String?,
    val context: ContextDTO,
    val language: String,
    val maki: String?,
    val poi_category: List<String>?,
    val external_ids: ExternalIds?
)

data class ContextDTO(
    val country: Country,
    val region: Region,
    val postcode: Postcode?,
    val place: Place,
    val neighborhood: Neighborhood?,
    val street: Street?
)

data class Country(val name: String, val country_code: String, val country_code_alpha3: String)
data class Region(val name: String, val region_code: String, val region_code_full: String)
data class Postcode(val name: String)
data class Place(val name: String)
data class Neighborhood(val name: String)
data class Street(val name: String)

data class ExternalIds(
    val safegraph: String?,
    val foursquare: String?
)
