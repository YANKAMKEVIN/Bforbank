package com.kevin.multiapiapp

import com.kevin.multiapiapp.domain.model.pokemon.PokemonInfosDomain
import com.kevin.multiapiapp.domain.model.pokemon.PokemonListDomain
import com.kevin.multiapiapp.domain.usecase.pokemon.GetAllPokemonUseCase
import com.kevin.multiapiapp.presentation.ui.pokemon.list.PokemonListViewModel
import com.kevin.multiapiapp.testing.MainDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class PokemonListViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var getAllPokemonUseCase: GetAllPokemonUseCase

    private lateinit var pokemonListViewModel: PokemonListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        pokemonListViewModel = PokemonListViewModel(getAllPokemonUseCase)
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `should load pokemon list successfully`() =
        runTest {
            // Given
            val pokemonList = listOf(
                PokemonInfosDomain(name = "Pikachu", url = "url_1"),
                PokemonInfosDomain(name = "Bulbasaur", url = "url_2")
            )
            val mockResponse = PokemonListDomain(
                count = 2,
                next = "next_url",
                previous = null,
                results = pokemonList
            )
            coEvery { getAllPokemonUseCase.invoke(limit = 20, offset = 0) } returns flowOf(
                mockResponse
            )

            // When
            pokemonListViewModel.loadPokemonList()

            // Then
            assertEquals(pokemonList, pokemonListViewModel.state.value.pokemonList)
            assertEquals(false, pokemonListViewModel.state.value.isLoading)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `should handle pagination and load more data`() =
        runTest {
            // Given
            val firstPageList = listOf(
                PokemonInfosDomain(name = "Pikachu", url = "url_1"),
                PokemonInfosDomain(name = "Bulbasaur", url = "url_2")
            )
            val firstPageResponse = PokemonListDomain(
                count = 2,
                next = "next_url?offset=2",
                previous = null,
                results = firstPageList
            )

            val secondPageList = listOf(
                PokemonInfosDomain(name = "Charmander", url = "url_3"),
                PokemonInfosDomain(name = "Squirtle", url = "url_4")
            )
            val secondPageResponse = PokemonListDomain(
                count = 2,
                next = null,
                previous = "next_url",
                results = secondPageList
            )

            coEvery { getAllPokemonUseCase.invoke(limit = 20, offset = 0) } returns flowOf(
                firstPageResponse
            )

            coEvery { getAllPokemonUseCase.invoke(limit = 20, offset = 2) } returns flowOf(
                secondPageResponse
            )

            //When
            pokemonListViewModel.loadPokemonList()

            // Then
            assertEquals(firstPageList, pokemonListViewModel.state.value.pokemonList)

            // Act: Load the next page
            pokemonListViewModel.loadPokemonList()

            // Assert: Check the final state with both pages loaded
            assertEquals(
                firstPageList + secondPageList,
                pokemonListViewModel.state.value.pokemonList
            )
        }
}
