package com.kevin.multiapiapp.common.navigation.graph

import com.kevin.multiapiapp.common.POKEMON_ID
import com.kevin.multiapiapp.common.TRACK_JSON

sealed class GraphDestination(val route: String) {
    data object Home : GraphDestination(
        route = "home_screen"
    )

    data object PokemonList : GraphDestination("poke_list_screen")
    data object PokemonDetails : GraphDestination("poke_details_screen/{$POKEMON_ID}") {
        fun withPokeId(pokemonId: String) = "poke_details_screen/$pokemonId"
    }

    data object Spotify : GraphDestination("spotify_screen")
    data object SpotifyTrackDetails : GraphDestination("spotify_track_details/{$TRACK_JSON}") {
        fun withTrackJson(trackJson: String): String {
            return "spotify_track_details/$trackJson"
        }
    }

    data object Unsplash : GraphDestination(
        "unsplash_screen"
    )

    data object Mapbox : GraphDestination("mapbox_screen")

    companion object {
        private val destinations = listOf(
            Home,
            PokemonList,
            PokemonDetails,
            Spotify,
            SpotifyTrackDetails,
            Unsplash,
            Mapbox
        )
        val backButtonDestinations =
            setOf(SpotifyTrackDetails, Spotify, Unsplash, Mapbox, PokemonDetails, PokemonList)
        val withAddListDestinations = setOf(SpotifyTrackDetails)
        val withMoreDestinations = setOf(SpotifyTrackDetails)

        fun fromRoute(route: String?): GraphDestination? {
            return destinations.find { it.route == route }
        }
    }

}