package com.kevin.multiapiapp.data.datasource.pokemon.impl

import com.kevin.multiapiapp.common.network.NetworkResponse
import com.kevin.multiapiapp.common.network.NetworkUtil
import com.kevin.multiapiapp.data.api.pokemon.PokemonApi
import com.kevin.multiapiapp.data.datasource.pokemon.PokemonDataSource
import com.kevin.multiapiapp.data.model.pokemon.PokemonListResponse
import javax.inject.Inject


/**
 * Implementation of the [PokemonDataSource] interface.
 * This class is responsible for fetching Pokémon data from the [PokemonApi]
 * and wrapping the responses in a [NetworkResponse].
 *
 * @param pokemonApi The API service used to fetch data.
 */

class PokemonDataSourceImpl @Inject constructor(
    private val pokemonApi: PokemonApi
) : PokemonDataSource {

    /**
     * Fetches a list of all Pokémon from the API.
     * This method wraps the API response in a [NetworkResponse] to handle success or failure.
     *
     * @param limit The number of Pokémon to fetch.
     * @param offset The starting point for the pagination.
     * @return A [NetworkResponse] wrapping a [PokemonListResponse] containing the list of Pokémon.
     */
    override suspend fun getAllPokemon(
        limit: Int,
        offset: Int
    ): NetworkResponse<PokemonListResponse> =
        NetworkUtil.executeApiCall {
            pokemonApi.getAllPokemon(limit, offset)
        }
}
