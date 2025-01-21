package com.kevin.multiapiapp.presentation.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kevin.multiapiapp.common.R
import com.kevin.multiapiapp.common.navigation.graph.GraphDestination

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.explore_apis),
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 32.sp,
                color = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.padding(bottom = 40.dp, top = 20.dp)
        )

        ApiCard(
            backgroundColor = Color(0xFFE91E63),
            iconResId = R.drawable.ic_spotify,
            title = stringResource(id = R.string.spotify_api),
            description = stringResource(id = R.string.spotify_description),
            onClick = { navController.navigate(GraphDestination.Spotify.route) },
            buttonText = stringResource(id = R.string.go_to_spotify)
        )

        ApiCard(
            backgroundColor = Color(0xFF6200EE),
            iconResId = R.drawable.ic_unsplash,
            title = stringResource(id = R.string.unsplash_api),
            description = stringResource(id = R.string.unsplash_description),
            onClick = { navController.navigate(GraphDestination.Unsplash.route) },
            buttonText = stringResource(id = R.string.go_to_unsplash)
        )

        ApiCard(
            backgroundColor = Color(0xFFFFEB3B),
            iconResId = R.drawable.ic_pokemon,
            title = stringResource(id = R.string.poke_api),
            description = stringResource(id = R.string.poke_description),
            onClick = { navController.navigate(GraphDestination.PokemonList.route) },
            buttonText = stringResource(id = R.string.go_to_pokemon),
            isPokeApiCard = true
        )

        ApiCard(
            backgroundColor = Color(0xFF03DAC5),
            iconResId = R.drawable.ic_mapbox,
            title = stringResource(id = R.string.mapbox_api),
            description = stringResource(id = R.string.mapbox_description),
            onClick = { navController.navigate(GraphDestination.Mapbox.route) },
            buttonText = stringResource(id = R.string.go_to_mapbox)
        )
    }
}