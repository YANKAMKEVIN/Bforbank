package com.kevin.multiapiapp.data.repository

import com.kevin.multiapiapp.common.network.NetworkResponse
import com.kevin.multiapiapp.data.datasource.pokemon.PokemonDataSource
import com.kevin.multiapiapp.data.mapper.pokemon.PokemonDetailsMapper
import com.kevin.multiapiapp.data.mapper.pokemon.PokemonListMapper
import com.kevin.multiapiapp.domain.model.pokemon.PokemonDetailsDomain
import com.kevin.multiapiapp.domain.model.pokemon.PokemonListDomain
import com.kevin.multiapiapp.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Implementation of the [PokemonRepository] interface.
 * This class is responsible for processing the data retrieved from the [PokemonDataSource]
 * and transforming it into the domain model before passing it to the use cases.
 */
class PokemonRepositoryImpl @Inject constructor(
    private val pokemonDataSource: PokemonDataSource
) : PokemonRepository {

    /**
     * Fetches a list of all Pokémon from the data source and maps the result into the domain model.
     * The result is emitted as a [Flow] containing a [PokemonListDomain].
     *
     * @param limit The number of Pokémon to retrieve in one request.
     * @param offset The offset for pagination, indicating the starting point for the data retrieval.
     * @return A [Flow] emitting a [PokemonListDomain] containing the list of Pokémon.
     */
    override suspend fun getAllPokemon(limit: Int, offset: Int): Flow<PokemonListDomain> =
        flow {
            when (val response = pokemonDataSource.getAllPokemon(limit, offset)) {
                is NetworkResponse.Success -> emit(PokemonListMapper.mapToDomain(response.data))
                is NetworkResponse.Failure -> {
                    throw Exception("Error: ${response.error.message}")
                }
            }
        }

    /**
     * Fetches the details of a specific Pokémon from the data source and maps the result into the domain model.
     * The result is emitted as a [Flow] containing a [PokemonDetailsDomain].
     *
     * @param id The ID of the Pokémon whose details are requested.
     * @return A [Flow] emitting a [PokemonDetailsDomain] containing the details of the Pokémon.
     */
    override suspend fun getPokemonDetails(id: Int): Flow<PokemonDetailsDomain> =
        flow {
            when (val response = pokemonDataSource.getPokemonDetails(id)) {
                is NetworkResponse.Success -> emit(PokemonDetailsMapper.mapToDomain(response.data))
                is NetworkResponse.Failure -> {
                    throw Exception("Error: ${response.error.message}")
                }
            }
        }
}