package com.kevin.multiapiapp.data.datasource.pokemon

import com.kevin.multiapiapp.common.network.NetworkResponse
import com.kevin.multiapiapp.data.model.pokemon.PokemonDetailsResponse
import com.kevin.multiapiapp.data.model.pokemon.PokemonListResponse

/**
 * Interface defining the data source for Pokémon-related data.
 * The implementation of this interface will provide methods to fetch Pokémon data
 * either as a list of all Pokémon or the details of a specific Pokémon.
 */
interface PokemonDataSource {

    /**
     * Fetches a list of all Pokémon with pagination support.
     * This method should be implemented to make the appropriate network call
     * and return the results as a [NetworkResponse] wrapped in a [PokemonListResponse].
     *
     * @param limit The number of Pokémon to retrieve in one request.
     * @param offset The offset used for pagination, indicating the starting point for the data retrieval.
     * @return A [NetworkResponse] containing a [PokemonListResponse] with the list of Pokémon.
     */
    suspend fun getAllPokemon(limit: Int, offset: Int): NetworkResponse<PokemonListResponse>

    /**
     * Fetches the details of a specific Pokémon by its ID.
     * This method should be implemented to fetch detailed information about a Pokémon.
     *
     * @param id The ID of the Pokémon whose details are requested.
     * @return A [NetworkResponse] containing a [PokemonDetailsResponse] with the details of the Pokémon.
     */
    suspend fun getPokemonDetails(id: Int): NetworkResponse<PokemonDetailsResponse>

}
