package com.kevin.multiapiapp.ui

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kevin.multiapiapp.common.POKEMON_ID
import com.kevin.multiapiapp.common.TRACK_JSON
import com.kevin.multiapiapp.common.navigation.graph.GraphDestination
import com.kevin.multiapiapp.presentation.ui.home.HomeScreen
import com.kevin.multiapiapp.presentation.ui.mapbox.MapboxScreen
import com.kevin.multiapiapp.presentation.ui.pokemon.details.PokemonDetailsScreen
import com.kevin.multiapiapp.presentation.ui.pokemon.list.PokemonListScreen
import com.kevin.multiapiapp.presentation.ui.spotify.SpotifyScreen
import com.kevin.multiapiapp.presentation.ui.spotify.SpotifyTrackScreen
import com.kevin.multiapiapp.presentation.ui.unsplash.UnsplashScreen
import com.kevin.multiapiapp.ui.top_bar.MultiApiTopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MultiApiApp() {
    val navController = rememberNavController()
    val currentDestination = remember { mutableStateOf<GraphDestination?>(null) }
    navController.addOnDestinationChangedListener { _, destination, _ ->
        currentDestination.value = GraphDestination.fromRoute(destination.route)
    }

    Scaffold(
        topBar = {
            MultiApiTopBar(
                onBackClick = { navController.popBackStack() },
                withAddList = GraphDestination.withAddListDestinations.contains(currentDestination.value),
                withMore = GraphDestination.withMoreDestinations.contains(currentDestination.value),
                withBackButton = GraphDestination.backButtonDestinations.contains(currentDestination.value)
            )
        },
        content = { AppNavHost(navController = navController) }
    )
}

@Composable
fun AppNavHost(navController: NavHostController) {

    NavHost(navController = navController, startDestination = GraphDestination.Home.route) {
        composable(GraphDestination.Home.route) { HomeScreen(navController) }
        composable(GraphDestination.PokemonList.route) { PokemonListScreen(navController) }
        composable(GraphDestination.PokemonDetails.route) { backStackEntry ->
            val pokemonId = backStackEntry.arguments?.getString(POKEMON_ID)
            PokemonDetailsScreen(
                pokemonId = pokemonId?.toIntOrNull()
            )
        }
        composable(GraphDestination.Spotify.route) { SpotifyScreen(navController = navController) }
        composable(GraphDestination.SpotifyTrackDetails.route) { backStackEntry ->
            val trackJson = backStackEntry.arguments?.getString(TRACK_JSON)
            SpotifyTrackScreen(trackJson = trackJson)
        }
        composable(GraphDestination.Unsplash.route) { UnsplashScreen(navController) }
        composable(GraphDestination.Mapbox.route) { MapboxScreen() }
    }
}