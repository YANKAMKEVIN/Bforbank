package com.kevin.multiapiapp.ui.top_bar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kevin.multiapiapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiApiTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Row (modifier = Modifier.fillMaxWidth()){
                Box(Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                    Icon(
                        painterResource(id = R.drawable.ic_logo_app),
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = stringResource(R.string.app_name),
                        modifier = Modifier.align(Alignment.Center),
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                }

            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF15171C)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    )
}