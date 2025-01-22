package com.kevin.multiapiapp.presentation.ui.unsplash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.kevin.multiapiapp.common.R
import com.kevin.multiapiapp.common.utils.reachedBottom

@Composable
fun UnsplashScreen(
    navController: NavHostController,
    viewModel: UnsplashViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val scrollState = rememberLazyListState()
    val reachedBottom: Boolean by remember { derivedStateOf { scrollState.reachedBottom() } }
    var showDialog by remember { mutableStateOf(false) }
    var selectedPhotoUrl by remember { mutableStateOf("") }

    LaunchedEffect(reachedBottom) {
        if (reachedBottom && !state.isLoading) {
            viewModel.searchPhotos()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF6200EE),
                        Color(0xFF020000)
                    )
                )
            )
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.unsplash_title_search_photos),
            style = MaterialTheme.typography.headlineMedium.copy(color = Color.White),
            modifier = Modifier.padding(bottom = 16.dp, top = 24.dp)
        )
        Text(
            text = stringResource(R.string.unsplash_description_search_photos),
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
        )
        TextField(
            value = state.query,
            onValueChange = {
                viewModel.onQueryChanged(it)
                viewModel.searchPhotos()
            },
            label = { Text(stringResource(R.string.unsplash_title_search_photos)) },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { viewModel.searchPhotos() }),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (state.photos.isEmpty() && !state.isLoading && state.query.isNotBlank()) {
            Text(text = stringResource(R.string.unsplash_text_no_photos_found))
        }
        if (state.photos.isEmpty() && !state.isLoading && state.query.isBlank()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_unsplash),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(color = Color.White),
                    modifier = Modifier.size(100.dp)
                )
            }

        }
        LazyColumn(state = scrollState) {
            items(state.photos) { photo ->
                UnsplashPhotoCard(photo) {
                    selectedPhotoUrl = photo.urls.regular
                    showDialog = true
                }
            }
            item {
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (showDialog) {
            FullScreenImageDialog(
                imageUrl = selectedPhotoUrl,
                onDismiss = { showDialog = false }
            )
        }
    }
}

@Composable
fun FullScreenImageDialog(imageUrl: String, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillWidth
            )
            IconButton(
                onClick = onDismiss,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_cancel),
                    contentDescription = stringResource(R.string.unsplash_dialog_close),
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}