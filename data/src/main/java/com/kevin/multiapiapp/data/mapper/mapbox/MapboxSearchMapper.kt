package com.kevin.multiapiapp.data.mapper.mapbox

import com.kevin.multiapiapp.data.model.mapbox.MapboxSearchDTO
import com.kevin.multiapiapp.data.model.mapbox.SuggestionDTO
import com.kevin.multiapiapp.domain.model.mapbox.MapboxSearchDomain
import com.kevin.multiapiapp.domain.model.mapbox.SuggestionDomain

object MapboxSearchMapper {

    // Map MapboxSearchResponse to MapboxSearchDomain
    fun mapToDomain(response: MapboxSearchDTO): MapboxSearchDomain {
        return MapboxSearchDomain(
            suggestions = response.suggestions.map { it.toDomain() }
        )
    }

    // Extension function to map Suggestion to SuggestionDomain
    private fun SuggestionDTO.toDomain(): SuggestionDomain {
        return SuggestionDomain(
            name = this.name,
            mapbox_id = this.mapbox_id,
            address = this.address,
            full_address = this.full_address
        )
    }
}
