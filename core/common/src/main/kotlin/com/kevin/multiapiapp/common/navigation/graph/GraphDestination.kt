package com.kevin.multiapiapp.common.navigation.graph

import com.kevin.multiapiapp.common.POKEMON_ID

sealed class GraphDestination(val route: String) {
    data object Home : GraphDestination(
        route = "home_screen"
    )
    data object PokemonList : GraphDestination("poke_list_screen")
    data object PokemonDetails : GraphDestination("poke_details_screen/{$POKEMON_ID}") {
        fun withPokeId(pokemonId: String) = "poke_details_screen/$pokemonId"
    }
}