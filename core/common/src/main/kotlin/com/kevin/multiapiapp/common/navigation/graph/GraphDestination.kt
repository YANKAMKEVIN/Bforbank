package com.kevin.multiapiapp.common.navigation.graph

sealed class GraphDestination(val route: String) {
    data object Home : GraphDestination(
        route = "home_screen"
    )
    data object PokemonList : GraphDestination("poke_list_screen")
}