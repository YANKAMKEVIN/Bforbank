package com.kevin.multiapiapp.presentation.ui.spotify

import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.gson.Gson
import com.kevin.multiapiapp.common.R
import com.kevin.multiapiapp.domain.model.spotify.TrackDomain
import java.time.Duration

@Composable
fun SpotifyTrackScreen(trackJson: String?) {
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
            .padding(horizontal = 10.dp)
    ) {
        val track = try {
            trackJson?.let { Gson().fromJson(Uri.decode(it), TrackDomain::class.java) }
        } catch (e: Exception) {
            Log.e("SpotifyTrackDetails", "Failed to parse trackJson", e)
            null
        }

        track?.let {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.height(30.dp))
                Image(
                    painter = rememberAsyncImagePainter(track.album.images.firstOrNull()?.url),
                    contentDescription = "Song banner",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .sizeIn(maxWidth = 500.dp, maxHeight = 500.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .weight(10f)
                )
                Spacer(modifier = Modifier.height(30.dp))
                SongDescription(track.name, track.artists.joinToString { it.name })
                Spacer(modifier = Modifier.height(35.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(10f)
                ) {
                    PlayerSlider(Duration.ofHours(2))
                    Spacer(modifier = Modifier.height(40.dp))
                    PlayerButtons(modifier = Modifier.padding(vertical = 8.dp))
                }
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun PlayerButtons(
    modifier: Modifier = Modifier,
    playerButtonSize: Dp = 72.dp,
    sideButtonSize: Dp = 42.dp
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val buttonModifier = Modifier
            .size(sideButtonSize)
            .semantics { role = Role.Button }

        Image(
            painter = painterResource(R.drawable.ic_rewind),
            contentDescription = stringResource(R.string.spotify_track_skip_previous_description),
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(Color.White),
            modifier = buttonModifier
        )

        Image(
            painter = painterResource(R.drawable.ic_skip_15_seconds),
            contentDescription = stringResource(R.string.spotify_track_forward_ten_description),
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(Color.White),
            modifier = buttonModifier
        )

        Image(
            imageVector = Icons.Filled.PlayArrow,
            contentDescription = stringResource(R.string.spotify_track_skip_play_description),
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier
                .size(playerButtonSize)
                .semantics { role = Role.Button }
        )

        Image(
            painter = painterResource(R.drawable.ic_time_forward_ten),
            contentDescription = stringResource(R.string.spotify_track_forward_ten_description),
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(Color.White),
            modifier = buttonModifier
        )

        Image(
            painter = painterResource(R.drawable.ic_forward),
            contentDescription = stringResource(R.string.spotify_track_skip_next_description),
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(Color.White),
            modifier = buttonModifier
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlayerSlider(ofHours: Duration?) {
    if (ofHours != null) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Slider(
                value = 0f,
                onValueChange = {},
                colors = SliderDefaults.colors(
                    thumbColor = Color.White,
                    activeTrackColor = Color.White
                )
            )
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "0s", color = Color.White)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "${ofHours.seconds}s", color = Color.White)
            }
        }
    }
}

@Composable
fun SongDescription(title: String, name: String) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}