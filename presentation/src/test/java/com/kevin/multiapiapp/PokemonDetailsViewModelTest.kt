package com.kevin.multiapiapp

import com.kevin.multiapiapp.domain.model.pokemon.AbilityDomain
import com.kevin.multiapiapp.domain.model.pokemon.PokemonDetailsDomain
import com.kevin.multiapiapp.domain.model.pokemon.ShowdownDomain
import com.kevin.multiapiapp.domain.model.pokemon.SpritesDomain
import com.kevin.multiapiapp.domain.model.pokemon.StatDomain
import com.kevin.multiapiapp.domain.model.pokemon.TypeDomain
import com.kevin.multiapiapp.domain.usecase.pokemon.GetPokemonDetailsUseCase
import com.kevin.multiapiapp.presentation.ui.pokemon.details.PokemonDetailsViewModel
import com.kevin.multiapiapp.testing.MainDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

@ExperimentalCoroutinesApi
class PokemonDetailsViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var getPokemonDetailsUseCase: GetPokemonDetailsUseCase

    private lateinit var pokemonDetailsViewModel: PokemonDetailsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        pokemonDetailsViewModel = PokemonDetailsViewModel(getPokemonDetailsUseCase)
    }

    @Test
    fun `should load pokemon details successfully`() = runTest {
        // Given
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

        coEvery { getPokemonDetailsUseCase(1) } returns flowOf(pokemonDetails)

        // When
        pokemonDetailsViewModel.getPokemonDetails(1)

        // Then
        assertEquals(pokemonDetails, pokemonDetailsViewModel.state.value.pokemonDetails)
        assertEquals(false, pokemonDetailsViewModel.state.value.isLoading)
        assertNull(pokemonDetailsViewModel.state.value.errorMessage)
    }

    @Test
    fun `should handle failure case properly when loading pokemon details`() = runTest {
        // Given
        coEvery { getPokemonDetailsUseCase(1) } returns flow { throw Exception("Network error") }

        // When
        pokemonDetailsViewModel.getPokemonDetails(1)

        // Then
        assertEquals("Network error", pokemonDetailsViewModel.state.value.errorMessage)
        assertEquals(false, pokemonDetailsViewModel.state.value.isLoading)
        assertNull(pokemonDetailsViewModel.state.value.pokemonDetails)
    }

}