package com.beeldi.beelding.ui.equipment_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import com.beeldi.beelding.R

@Composable
fun EquipmentDetailView(
    modifier: Modifier = Modifier,
    equipmentKey: String,
    onBackToList: () -> Unit
){

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.small_space)),
        horizontalAlignment = Alignment.Start
    ) {
        Box(
           modifier = Modifier
               .fillMaxWidth()
               .fillMaxHeight(1 / 2f)
               .background(Color.Blue)
        ) {

        }
        Text(
            text = "equipment.name",
            style = MaterialTheme.typography.titleMedium,
            overflow = TextOverflow.Clip
        )
    }
}