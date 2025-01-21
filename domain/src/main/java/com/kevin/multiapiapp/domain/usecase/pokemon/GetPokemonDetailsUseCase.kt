package com.kevin.multiapiapp.domain.usecase.pokemon

import com.kevin.multiapiapp.domain.model.pokemon.PokemonDetailsDomain
import kotlinx.coroutines.flow.Flow

/**
 * Use case for fetching the details of a specific Pokémon.
 * This use case represents the business logic of retrieving Pokémon details
 * and abstracts away the data layer.
 */
interface GetPokemonDetailsUseCase {

    /**
     * Retrieves the details of a Pokémon based on its unique ID.
     *
     * @param id The unique identifier of the Pokémon whose details are to be fetched.
     * @return A [Flow] emitting the details of the Pokémon as a [PokemonDetailsDomain] object.
     */
    suspend operator fun invoke(id: Int): Flow<PokemonDetailsDomain>
}