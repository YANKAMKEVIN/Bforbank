package com.kevin.multiapiapp.presentation.ui.mapbox

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.kevin.multiapiapp.common.R

@Composable
fun MapboxScreen(
    mapboxViewModel: MapboxViewModel = hiltViewModel()
) {
    val state = mapboxViewModel.state.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF03DAC5),
                        Color(0xFF020000)
                    )
                )
            )
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.mapbox_title_search_location),
            style = MaterialTheme.typography.headlineMedium.copy(Color.White),
            modifier = Modifier.padding(bottom = 16.dp, top = 24.dp)
        )
        Text(
            text = stringResource(R.string.unsplash_description_search_photos),
            style = MaterialTheme.typography.bodyMedium.copy(Color.White),
        )

        TextField(
            value = state.query,
            onValueChange = { value ->
                mapboxViewModel.onQueryChanged(value)
                mapboxViewModel.search(value)
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                mapboxViewModel.search(state.query)
            }),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .padding(top = 24.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (!state.isLoading && state.query.isBlank()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_mapbox),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(color = Color.White),
                    modifier = Modifier.size(100.dp)
                )
            }

        }

        when {
            state.selectedSuggestion != null -> {
                if (state.locationDetails != null)
                    if (state.locationDetails.properties.isNotEmpty())
                        LocationDetailCard(locationDetails = state.locationDetails)
            }

            else -> {
                if (state.isLoading) {
                    CircularProgressIndicator()
                } else {
                    LocationList(
                        suggestions = state.suggestions,
                        onLocationClick = {
                            mapboxViewModel.onSuggestionSelected(it)
                        }
                    )
                }
            }
        }

        state.errorMessage?.let {
            if (state.query.isNotBlank())
                Text(text = "Error: $it", color = MaterialTheme.colorScheme.error)
        }
    }
}