package com.kevin.multiapiapp

import com.kevin.multiapiapp.domain.model.pokemon.AbilityDomain
import com.kevin.multiapiapp.domain.model.pokemon.PokemonDetailsDomain
import com.kevin.multiapiapp.domain.model.pokemon.ShowdownDomain
import com.kevin.multiapiapp.domain.model.pokemon.SpritesDomain
import com.kevin.multiapiapp.domain.model.pokemon.StatDomain
import com.kevin.multiapiapp.domain.model.pokemon.TypeDomain
import com.kevin.multiapiapp.domain.repository.PokemonRepository
import com.kevin.multiapiapp.domain.usecase.pokemon.impl.GetPokemonDetailsUseCaseImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class GetPokemonDetailsUseCaseImplTest {

    private lateinit var getPokemonDetailsUseCase: GetPokemonDetailsUseCaseImpl
    private val pokemonRepository: PokemonRepository = mockk()

    @Before
    fun setup() {
        getPokemonDetailsUseCase = GetPokemonDetailsUseCaseImpl(pokemonRepository)
    }

    @Test
    fun `should fetch pokemon details successfully`() = runTest {
        // Arrange
        val pokemonDetails = PokemonDetailsDomain(
            name = "Pikachu",
            sprites = SpritesDomain(
                back_default = "back_default_url",
                front_default = "front_default_url",
                showdown = ShowdownDomain(front_default = "showdown_url")
            ),
            cries = "pikachu_cries_url",
            height = 4,
            weight = 60,
            stats = listOf(StatDomain(base_stat = 55)),
            types = listOf(TypeDomain(type = "electric")),
            abilities = listOf(AbilityDomain(ability = "static"))
        )

        coEvery { pokemonRepository.getPokemonDetails(id = 1) } returns flow {
            emit(pokemonDetails)
        }

        // Act & Assert
        getPokemonDetailsUseCase.invoke(id = 1).collect { emittedValue ->
            assertEquals(pokemonDetails, emittedValue)
        }
        coVerify { pokemonRepository.getPokemonDetails(1) }
    }

    @Test
    fun `should handle empty pokemon details gracefully`() = runTest {
        // Arrange
        val emptyPokemonDetails = PokemonDetailsDomain(
            name = "",
            sprites = SpritesDomain(
                back_default = "",
                front_default = "",
                showdown = ShowdownDomain(front_default = "")
            ),
            cries = "",
            height = 0,
            weight = 0,
            stats = emptyList(),
            types = emptyList(),
            abilities = emptyList()
        )

        coEvery { pokemonRepository.getPokemonDetails(id = 1) } returns flow {
            emit(emptyPokemonDetails)
        }

        // Act & Assert
        getPokemonDetailsUseCase.invoke(id = 1).collect { emittedValue ->
            assertEquals(emptyPokemonDetails, emittedValue)
        }
        coVerify { pokemonRepository.getPokemonDetails(1) }
    }
}
