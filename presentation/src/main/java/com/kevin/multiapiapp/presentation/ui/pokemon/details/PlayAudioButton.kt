package com.kevin.multiapiapp.presentation.ui.pokemon.details

import android.media.MediaPlayer
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.kevin.multiapiapp.common.R

@Composable
fun PlayAudioButton(audioUrl: String) {
    var isPlaying by remember { mutableStateOf(false) }
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }

    LaunchedEffect(isPlaying) {
        if (isPlaying) {
            mediaPlayer = MediaPlayer().apply {
                setDataSource(audioUrl)
                prepare()
                start()
                setOnCompletionListener {
                    isPlaying = false
                    mediaPlayer?.release()
                    mediaPlayer = null
                }
            }
        } else {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    Button(
        onClick = { isPlaying = !isPlaying },
    ) {
        Text(if (isPlaying) stringResource(R.string.poke_stop_listen_cries) else stringResource(R.string.poke_listen_cries))
    }
}
