package com.kevin.multiapiapp.presentation.ui.mapbox

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kevin.multiapiapp.common.R
import com.kevin.multiapiapp.domain.model.mapbox.SuggestionDomain

@Composable
fun LocationList(suggestions: List<SuggestionDomain>, onLocationClick: (SuggestionDomain) -> Unit) {
    LazyColumn {
        items(suggestions) { suggestion ->
            suggestion.full_address?.let {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { onLocationClick(suggestion) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_location_pin),
                        contentDescription = null,
                        modifier = Modifier.padding(end = 8.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                    Text(text = it, color = Color.White)
                }
            }
        }
    }
}