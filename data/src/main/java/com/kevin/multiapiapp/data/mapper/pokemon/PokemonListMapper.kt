package com.kevin.multiapiapp.data.mapper.pokemon

import com.kevin.multiapiapp.data.model.pokemon.*
import com.kevin.multiapiapp.domain.model.pokemon.*

object PokemonListMapper {

    // Map PokemonListResponse to PokemonListDomain
    fun mapToDomain(response: PokemonListResponse): PokemonListDomain {
        return PokemonListDomain(
            count = response.count,
            next = response.next,
            previous = response.previous,
            results = response.results.map { it.toDomain() }
        )
    }
    // Extension function to map PokemonResult to PokemonInfosDomain
    private fun PokemonResult.toDomain(): PokemonInfosDomain {
        return PokemonInfosDomain(
            name = this.name,
            url = this.url
        )
    }
}
