package com.kevin.multiapiapp.domain.repository

import com.kevin.multiapiapp.domain.model.pokemon.PokemonListDomain
import kotlinx.coroutines.flow.Flow

/**
 * Interface defining the repository methods for fetching Pokémon data.
 * This interface abstracts the data layer and is used by use cases to interact
 * with the data source(s) without knowing the details of the data retrieval.
 */
interface PokemonRepository {

    /**
     * Fetches a list of all Pokémon, with support for pagination.
     *
     * @param limit The number of Pokémon to retrieve in one request. Default is 20.
     * @param offset The offset for pagination, indicating the starting point for the data retrieval.
     * @return A [Flow] emitting a [PokemonListDomain] containing the list of Pokémon.
     */
    suspend fun getAllPokemon(limit: Int = 20, offset: Int): Flow<PokemonListDomain>
}