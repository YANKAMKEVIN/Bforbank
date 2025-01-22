package com.kevin.multiapiapp.presentation.ui.spotify

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kevin.multiapiapp.common.R
import com.kevin.multiapiapp.common.navigation.graph.GraphDestination
import com.kevin.multiapiapp.common.utils.reachedBottom

@Composable
fun SpotifyScreen(
    navController: NavHostController,
    viewModel: SpotifyViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val scrollState = rememberLazyListState()
    val reachedBottom: Boolean by remember { derivedStateOf { scrollState.reachedBottom() } }

    LaunchedEffect(reachedBottom) {
        if (reachedBottom && !state.isLoading) {
            viewModel.searchTracks(state.query)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF131646),
                        Color(0xFF020000)
                    )
                )
            )
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.spotify_title_search_tracks),
            style = MaterialTheme.typography.headlineMedium.copy(color = Color.White),
            modifier = Modifier.padding(bottom = 16.dp, top = 32.dp)
        )
        Text(
            text = stringResource(R.string.spotify_description_search_tracks),
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = state.query,
            onValueChange = {
                viewModel.onQueryChanged(it)
                viewModel.searchTracks(it)
            },
            label = { Text(stringResource(R.string.spotify_title_search_tracks)) },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { viewModel.searchTracks(state.query) }),
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (state.errorMessage != null && state.query.isNotBlank()) {
            Text(text = "Error: ${state.errorMessage}", color = Color.Red)
        }

        if (state.tracks.isEmpty() && !state.isLoading && state.query.isNotBlank()) {
            Text(text = stringResource(R.string.spotify_text_no_tracks_found))
        }

        if (state.tracks.isEmpty() && !state.isLoading && state.query.isBlank()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_spotify),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(color = Color.White),
                    modifier = Modifier.size(100.dp)
                )
            }

        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = scrollState
        ) {
            items(state.tracks) { track ->
                SpotifyTrackCard(track) {
                    navController.navigate(GraphDestination.SpotifyTrackDetails.withTrackJson(it))
                }
            }
            if (state.isLoading) {
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
        Spacer(modifier = Modifier.height(16.dp))
    }
}