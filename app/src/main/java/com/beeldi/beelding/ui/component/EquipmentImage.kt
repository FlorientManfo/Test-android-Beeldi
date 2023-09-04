package com.beeldi.beelding.ui.component

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.beeldi.beelding.R

@Composable
fun EquipmentImage(
    data: Any,
    modifier: Modifier = Modifier
){
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(data)
            .crossfade(true)
            .build(),
        contentDescription = stringResource(id = R.string.equipment_image_desc),
        contentScale = ContentScale.FillBounds,
        modifier = modifier.fillMaxSize()
    )
}