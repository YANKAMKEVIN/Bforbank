package com.kevin.multiapiapp

import com.kevin.multiapiapp.domain.model.pokemon.PokemonInfosDomain
import com.kevin.multiapiapp.domain.model.pokemon.PokemonListDomain
import com.kevin.multiapiapp.domain.repository.PokemonRepository
import com.kevin.multiapiapp.domain.usecase.pokemon.impl.GetAllPokemonUseCaseImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class GetAllPokemonUseCaseImplTest {

    private lateinit var getAllPokemonUseCase: GetAllPokemonUseCaseImpl
    private val pokemonRepository: PokemonRepository = mockk()

    @Before
    fun setup() {
        getAllPokemonUseCase = GetAllPokemonUseCaseImpl(pokemonRepository)
    }

    @Test
    fun `should fetch pokemon list successfully`() = runTest {
        // Arrange
        val pokemonListDomain = PokemonListDomain(
            count = 2,
            next = "next_url",
            previous = null,
            results = listOf(
                PokemonInfosDomain(name = "Pikachu", url = "url_1"),
                PokemonInfosDomain(name = "Bulbasaur", url = "url_2")
            )
        )

        coEvery {
            pokemonRepository.getAllPokemon(
                limit = 20,
                offset = 0
            )
        } returns kotlinx.coroutines.flow.flow {
            emit(pokemonListDomain)
        }

        // Act
        val emittedValues = getAllPokemonUseCase.invoke(limit = 20, offset = 0).toList()

        // Assert
        assertEquals(1, emittedValues.size)
        assertEquals(pokemonListDomain, emittedValues.first())
    }

    @Test
    fun `should handle empty pokemon list`() = runTest {
        // Arrange
        val emptyPokemonList = PokemonListDomain(
            count = 0,
            next = null,
            previous = null,
            results = emptyList()
        )

        coEvery {
            pokemonRepository.getAllPokemon(
                limit = 20,
                offset = 0
            )
        } returns kotlinx.coroutines.flow.flow {
            emit(emptyPokemonList)
        }

        // Act
        val emittedValues = getAllPokemonUseCase.invoke(limit = 20, offset = 0).toList()

        // Assert
        assertEquals(1, emittedValues.size)
        assertEquals(emptyPokemonList, emittedValues.first())
    }

    @Test
    fun `should call repository with correct parameters`() =
        runTest {
            // Arrange
            val pokemonListDomain = PokemonListDomain(
                count = 2,
                next = "next_url",
                previous = null,
                results = listOf(
                    PokemonInfosDomain(name = "Pikachu", url = "url_1"),
                    PokemonInfosDomain(name = "Bulbasaur", url = "url_2")
                )
            )

            coEvery {
                pokemonRepository.getAllPokemon(
                    limit = 20,
                    offset = 0
                )
            } returns kotlinx.coroutines.flow.flow {
                emit(pokemonListDomain)
            }

            // Act
            val emittedValues = getAllPokemonUseCase.invoke(limit = 20, offset = 0).toList()

            // Assert
            assertEquals(1, emittedValues.size)
            assertEquals(pokemonListDomain, emittedValues.first())
        }
}
