package com.kevin.multiapiapp.mapper

import com.kevin.multiapiapp.data.mapper.pokemon.PokemonListMapper
import com.kevin.multiapiapp.data.model.pokemon.PokemonListResponse
import com.kevin.multiapiapp.data.model.pokemon.PokemonResult
import com.kevin.multiapiapp.domain.model.pokemon.PokemonListDomain
import org.junit.Assert.assertEquals
import org.junit.Test

class PokemonListMapperTest {

    @Test
    fun `mapToDomain should correctly map PokemonListResponse to PokemonListDomain`() {
        // Arrange: Create PokemonListResponse with test data
        val pokemon1 =
            PokemonResult(name = "Bulbasaur", url = "https://pokeapi.co/api/v2/pokemon/1/")
        val pokemon2 =
            PokemonResult(name = "Charmander", url = "https://pokeapi.co/api/v2/pokemon/4/")
        val pokemonListResponse = PokemonListResponse(
            count = 2,
            next = "https://pokeapi.co/api/v2/pokemon?offset=2&limit=2",
            previous = null,
            results = listOf(pokemon1, pokemon2)
        )

        // Act: Map the response to a domain object
        val result: PokemonListDomain = PokemonListMapper.mapToDomain(pokemonListResponse)

        // Assert: Verify the mapping results
        assertEquals(2, result.count)
        assertEquals("https://pokeapi.co/api/v2/pokemon?offset=2&limit=2", result.next)
        assertEquals(null, result.previous)

        // Verify individual Pokemon mappings
        val domainResults = result.results
        assertEquals(2, domainResults.size)

        // Verify Pokemon 1
        val domainPokemon1 = domainResults[0]
        assertEquals("Bulbasaur", domainPokemon1.name)
        assertEquals("https://pokeapi.co/api/v2/pokemon/1/", domainPokemon1.url)

        // Verify Pokemon 2
        val domainPokemon2 = domainResults[1]
        assertEquals("Charmander", domainPokemon2.name)
        assertEquals("https://pokeapi.co/api/v2/pokemon/4/", domainPokemon2.url)
    }
}
