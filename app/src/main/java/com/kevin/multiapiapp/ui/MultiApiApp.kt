package com.kevin.multiapiapp.ui

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kevin.multiapiapp.common.navigation.graph.GraphDestination
import com.kevin.multiapiapp.presentation.ui.home.HomeScreen
import com.kevin.multiapiapp.presentation.ui.pokemon.list.PokemonListScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MultiApiApp() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            //MultiApiTopBar()
        },
        content = { AppNavHost(navController = navController) }
    )
}

@Composable
fun AppNavHost(navController: NavHostController) {

    NavHost(navController = navController, startDestination = GraphDestination.Home.route) {
        composable(GraphDestination.Home.route) { HomeScreen(navController) }
        composable(GraphDestination.PokemonList.route) { PokemonListScreen(navController) }
    }
}