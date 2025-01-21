package com.kevin.multiapiapp.presentation.ui.spotify

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.kevin.multiapiapp.domain.model.spotify.TrackDomain
import com.kevin.multiapiapp.common.R

@Composable
fun SpotifyTrackCard(track: TrackDomain) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = rememberAsyncImagePainter(track.album.images.firstOrNull()?.url),
                contentDescription = stringResource(R.string.spotify_description_album_image),
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = track.name, style = MaterialTheme.typography.labelLarge)
                Text(text = track.artists.joinToString { it.name })
            }
        }
    }
}