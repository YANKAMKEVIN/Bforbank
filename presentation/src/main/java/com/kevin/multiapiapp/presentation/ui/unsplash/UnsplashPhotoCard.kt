package com.kevin.multiapiapp.presentation.ui.unsplash

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.kevin.multiapiapp.domain.model.unsplash.PhotoDomain
import com.kevin.multiapiapp.common.R

@Composable
fun UnsplashPhotoCard(photo: PhotoDomain, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(photo.urls.regular),
                contentDescription = stringResource(R.string.unsplash_image_description),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(16 / 9f)
            )
            photo.description?.let {
                Text(
                    text = photo.description ?: "",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}