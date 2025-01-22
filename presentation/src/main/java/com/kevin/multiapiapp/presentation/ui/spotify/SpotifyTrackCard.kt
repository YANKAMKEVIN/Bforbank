package com.kevin.multiapiapp.presentation.ui.spotify

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import com.google.gson.Gson
import com.kevin.multiapiapp.common.R
import com.kevin.multiapiapp.domain.model.spotify.TrackDomain

@Composable
fun SpotifyTrackCard(track: TrackDomain, onTrackClicked: (trackJson: String) -> Unit) {
    val gson = Gson()
    val trackJson = gson.toJson(track)
    val trackJsonEncoded = Uri.encode(trackJson)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onTrackClicked(trackJsonEncoded) }
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