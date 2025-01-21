package com.kevin.multiapiapp.presentation.ui.mapbox

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kevin.multiapiapp.common.R
import com.kevin.multiapiapp.domain.model.mapbox.MapboxRetrieveDomain

@Composable
fun LocationDetailCard(locationDetails: MapboxRetrieveDomain) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = locationDetails.properties.first().context.country
                    ?: stringResource(R.string.no_data_found),
                style = MaterialTheme.typography.titleLarge
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            Text(
                text = locationDetails.properties.first().context.place
                    ?: stringResource(R.string.no_data_found),
                style = MaterialTheme.typography.titleLarge
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            Text(
                text = locationDetails.properties.first().context.street
                    ?.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
                    ?: stringResource(R.string.no_data_found),
                style = MaterialTheme.typography.titleLarge
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            Text(
                text = locationDetails.properties.first().full_address
                    ?: stringResource(R.string.no_address_found),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            MapboxMap(
                latitude = locationDetails.properties.first().coordinates.latitude,
                longitude = locationDetails.properties.first().coordinates.longitude
            )
        }
    }
}