package com.kevin.multiapiapp.domain.model.mapbox

data class MapboxSearchDomain(
    val suggestions: List<SuggestionDomain>,
)

data class SuggestionDomain(
    val name: String,
    val mapbox_id: String,
    val address: String?,
    val full_address: String?,
)