package com.kevin.multiapiapp.domain.usecase.pokemon.impl

import com.kevin.multiapiapp.domain.model.pokemon.PokemonListDomain
import com.kevin.multiapiapp.domain.repository.PokemonRepository
import com.kevin.multiapiapp.domain.usecase.pokemon.GetAllPokemonUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Implementation of the [GetAllPokemonUseCase] interface.
 * This class interacts with the repository to fetch a list of Pokémon.
 * It encapsulates the logic of retrieving the data and formats it into a domain model
 * that can be used by the presentation layer.
 */
internal class GetAllPokemonUseCaseImpl(
    private val pokemonRepository: PokemonRepository
) : GetAllPokemonUseCase {
    /**
     * Retrieves a list of Pokémon from the repository with pagination support.
     *
     * @param limit The maximum number of Pokémon to retrieve in one request.
     * @param offset The starting position for fetching Pokémon (useful for pagination).
     * @return A [Flow] emitting the list of Pokémon as a [PokemonListDomain] object.
     */
    override suspend fun invoke(limit: Int, offset: Int): Flow<PokemonListDomain> =
        pokemonRepository.getAllPokemon(limit, offset)
}