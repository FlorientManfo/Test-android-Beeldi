package com.beeldi.beelding.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beeldi.beelding.R
import com.beeldi.beelding.domain.model.Equipment
import com.beeldi.beelding.ui.theme.BeeldingTheme

@Composable
fun EquipmentRow(
    modifier: Modifier = Modifier,
    equipment: Equipment,
    onclickCallback: (equipmentKey: String) -> Unit
){
    Card(
        shape = MaterialTheme.shapes.small,
        elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.small_space)),
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(dimensionResource(id = R.dimen.small_space))
            .clickable { onclickCallback(equipment.equipmentKey) }
    ){

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.medium_space)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.medium_size))
                    .weight(1 / 2f)
            ){
                EquipmentImage(equipment.photo)
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.small_space)),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = equipment.name,
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Clip
                )
                Text(
                    text = equipment.domain,
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Clip
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Badge(content = "${equipment.nbFaults}" )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EquipmentRowPreview(){
    BeeldingTheme() {
        EquipmentRow(
            equipment = Equipment("Test","Test","Test",
                "Test","Test","Test","0","Test",
                "Test","Test","Test","Test","Test",
            ),
            onclickCallback = {}
        )
    }
}