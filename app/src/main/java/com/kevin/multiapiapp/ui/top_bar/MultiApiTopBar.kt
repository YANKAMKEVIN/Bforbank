package com.kevin.multiapiapp.ui.top_bar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.kevin.multiapiapp.common.R

@Composable
fun MultiApiTopBar(
    onBackClick: () -> Unit,
    withAddList: Boolean = false,
    withMore: Boolean = false,
    withBackButton: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        if (withBackButton)
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.top_bar_back_arrow),
                    tint = Color.White
                )
            }

        Spacer(modifier = Modifier.weight(1f))
        if (withAddList)
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.top_bar_add_list),
                    tint = Color.White
                )
            }
        if (withMore)
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = stringResource(R.string.top_bar_more),
                    tint = Color.White
                )
            }
    }
}