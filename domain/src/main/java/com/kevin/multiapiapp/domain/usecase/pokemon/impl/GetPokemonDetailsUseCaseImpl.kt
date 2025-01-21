package com.kevin.multiapiapp.domain.usecase.pokemon.impl

import com.kevin.multiapiapp.domain.model.pokemon.PokemonDetailsDomain
import com.kevin.multiapiapp.domain.repository.PokemonRepository
import com.kevin.multiapiapp.domain.usecase.pokemon.GetPokemonDetailsUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Implementation of the [GetPokemonDetailsUseCase] interface.
 * This class interacts with the repository to fetch Pokémon details.
 * It encapsulates the logic for retrieving the data and transforms it into a format
 * suitable for the presentation layer.
 */
internal class GetPokemonDetailsUseCaseImpl(
    private val pokemonRepository: PokemonRepository
) : GetPokemonDetailsUseCase {

    /**
     * Retrieves the details of a Pokémon by calling the repository.
     *
     * @param id The unique identifier of the Pokémon whose details are to be retrieved.
     * @return A [Flow] emitting the details of the Pokémon as a [PokemonDetailsDomain] object.
     */
    override suspend fun invoke(id: Int): Flow<PokemonDetailsDomain> =
        pokemonRepository.getPokemonDetails(id)
}