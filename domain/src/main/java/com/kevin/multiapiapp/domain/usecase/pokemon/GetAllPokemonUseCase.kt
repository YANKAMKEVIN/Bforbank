package com.kevin.multiapiapp.domain.usecase.pokemon

import com.kevin.multiapiapp.domain.model.pokemon.PokemonListDomain
import kotlinx.coroutines.flow.Flow

/**
 * Defines the contract for fetching a list of Pokémon.
 * The use case interface abstracts the operation of retrieving Pokémon data,
 * allowing flexibility in how it is implemented (e.g., by repository).
 */
interface GetAllPokemonUseCase {
    /**
     * Fetches a list of Pokémon with pagination support.
     *
     * @param limit The maximum number of Pokémon to fetch in a single request.
     * @param offset The position to start fetching from, for pagination.
     * @return A [Flow] that emits a [PokemonListDomain] containing the list of Pokémon.
     */
    suspend operator fun invoke(limit: Int = 20, offset: Int): Flow<PokemonListDomain>
}