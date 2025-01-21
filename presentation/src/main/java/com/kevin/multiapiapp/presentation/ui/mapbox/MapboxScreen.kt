package com.kevin.multiapiapp.presentation.ui.mapbox

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kevin.multiapiapp.common.R

@Composable
fun MapboxScreen(mapboxViewModel: MapboxViewModel = hiltViewModel()) {
    val state = mapboxViewModel.state.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.mapbox_title_search_location),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp, top = 24.dp)
        )
        Text(
            text = stringResource(R.string.unsplash_description_search_photos),
            style = MaterialTheme.typography.bodyMedium,
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

@Preview
@Composable
fun PreviewMapScreen() {
    MapboxScreen()
}