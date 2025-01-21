package com.kevin.multiapiapp.mapper

import com.kevin.multiapiapp.data.model.mapbox.MapboxSearchDTO
import com.kevin.multiapiapp.data.model.mapbox.SuggestionDTO
import com.kevin.multiapiapp.domain.model.mapbox.MapboxSearchDomain
import com.kevin.multiapiapp.data.mapper.mapbox.MapboxSearchMapper
import com.kevin.multiapiapp.data.model.mapbox.ContextDTO
import com.kevin.multiapiapp.data.model.mapbox.Country
import com.kevin.multiapiapp.data.model.mapbox.ExternalIds
import com.kevin.multiapiapp.data.model.mapbox.Neighborhood
import com.kevin.multiapiapp.data.model.mapbox.Place
import com.kevin.multiapiapp.data.model.mapbox.Postcode
import com.kevin.multiapiapp.data.model.mapbox.Region
import com.kevin.multiapiapp.data.model.mapbox.Street
import org.junit.Assert.assertEquals
import org.junit.Test

class MapboxSearchMapperTest {

    @Test
    fun `mapToDomain should correctly map MapboxSearchDTO to MapboxSearchDomain`() {
        // Arrange: Create a MapboxSearchDTO with test data
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

        val mapboxSearchDTO = MapboxSearchDTO(
            suggestions = listOf(suggestion1, suggestion2),
            attribution = "Mapbox"
        )

        // Act: Map the DTO to a Domain object
        val result: MapboxSearchDomain = MapboxSearchMapper.mapToDomain(mapboxSearchDTO)

        // Assert: Verify the mapping results
        assertEquals(2, result.suggestions.size)

        val domainSuggestion1 = result.suggestions[0]
        assertEquals("Eiffel Tower", domainSuggestion1.name)
        assertEquals("mbx123", domainSuggestion1.mapbox_id)
        assertEquals("Champ de Mars", domainSuggestion1.address)
        assertEquals("Champ de Mars, 5 Avenue Anatole France, 75007 Paris, France", domainSuggestion1.full_address)

        val domainSuggestion2 = result.suggestions[1]
        assertEquals("Louvre Museum", domainSuggestion2.name)
        assertEquals("mbx456", domainSuggestion2.mapbox_id)
        assertEquals("Rue de Rivoli", domainSuggestion2.address)
        assertEquals("Rue de Rivoli, 75001 Paris, France", domainSuggestion2.full_address)
    }
}
