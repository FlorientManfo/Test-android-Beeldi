package com.beeldi.beelding.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.beeldi.beelding.R
import com.beeldi.beelding.domain.model.Checkpoint

@Composable
fun CheckpointSection(
    modifier: Modifier = Modifier,
    checkpoints: List<Checkpoint>){

    Column(
        modifier = modifier.padding(dimensionResource(id = R.dimen.small_space))
    ) {
       for (item in checkpoints){
           Divider()
           Row(
               modifier = Modifier.fillMaxWidth(),
               verticalAlignment = Alignment.CenterVertically,
               horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.small_space))
           ) {
               Box(
                   modifier = Modifier
                       .size(dimensionResource(id = R.dimen.medium_size))
                       .weight(1 / 3f)
               ) {
                   EquipmentImage(data = item.photo)
               }

               Column(
                   verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.small_space)),
                   horizontalAlignment = Alignment.Start,
                   modifier = Modifier
                       .weight(1f)
               ) {
                   Text(
                       text = item.name,
                       style = MaterialTheme.typography.bodyMedium,
                       overflow = TextOverflow.Clip,
                       color = MaterialTheme.colorScheme.secondary,
                       fontWeight = FontWeight.Bold
                   )
                   Text(
                       text = item.fault,
                       style = MaterialTheme.typography.bodySmall,
                       overflow = TextOverflow.Clip,
                       color = MaterialTheme.colorScheme.secondary
                   )
               }
           }
       }
    }

}