package com.kevin.multiapiapp.presentation.ui.pokemon.list

import PokemonItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kevin.multiapiapp.common.R
import com.kevin.multiapiapp.common.navigation.graph.GraphDestination
import com.kevin.multiapiapp.common.utils.reachedBottom

@Composable
fun PokemonListScreen(
    navController: NavHostController,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value
    val scrollState = rememberLazyListState()
    val reachedBottom: Boolean by remember { derivedStateOf { scrollState.reachedBottom() } }
    LaunchedEffect(reachedBottom) {
        if (reachedBottom && !state.isLoadingMore) viewModel.loadPokemonList()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFA92F2F),
                        Color(0xFF020000)
                    )
                )
            )
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            state.errorMessage != null -> {
                Text(
                    text = state.errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .padding(16.dp)
                )
            }

            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = stringResource(R.string.poke_title),
                            style = MaterialTheme.typography.headlineMedium.copy(color = Color.White),
                            modifier = Modifier.padding(bottom = 16.dp, top = 32.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_pikachu),
                            contentDescription = "",
                            modifier = Modifier.size(50.dp),
                        )
                    }

                    Text(
                        text = stringResource(R.string.poke_description),
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalAlignment = Alignment.Start,
                        state = scrollState
                    ) {
                        items(state.pokemonList) { pokemon ->
                            PokemonItem(pokemon = pokemon) {
                                val pokemonId = extractPokemonId(pokemon.url)
                                pokemonId?.let {
                                    navController.navigate(
                                        GraphDestination.PokemonDetails.withPokeId(
                                            pokemonId
                                        )
                                    )
                                }
                            }
                        }

                        if (state.isLoadingMore) {
                            item {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun extractPokemonId(url: String): String? {
    val regex =
        """/pokemon/(\d+)/""".toRegex()
    val matchResult = regex.find(url)
    val pokemonId = matchResult?.groupValues?.get(1)
    return pokemonId
}
