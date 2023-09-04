package com.beeldi.beelding.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import com.beeldi.beelding.R

@Composable
fun LoadingComponent(
    modifier: Modifier = Modifier,
    message: String
){
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = MaterialTheme.shapes.medium,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(dimensionResource(id = R.dimen.medium_space))
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.medium_space)),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.medium_space))
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(dimensionResource(id = R.dimen.medium_size)),
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Loading ${message}...",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}