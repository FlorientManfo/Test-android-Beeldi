package com.beeldi.beelding.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.beeldi.beelding.R
import com.beeldi.beelding.domain.model.Equipment

@Composable
fun InfoSection(
    modifier: Modifier = Modifier,
    equipment: Equipment?
){
    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.medium_space)),
        modifier = modifier.padding(dimensionResource(id = R.dimen.small_space))
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.small_space)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.small_space))
        ) {
            Text(
                text = "${equipment?.name}",
                style = MaterialTheme.typography.titleLarge,
                overflow = TextOverflow.Clip
            )
            Text(
                text = "${equipment?.domain}",
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Clip
            )
            val info = mapOf(
                "Brand : " to "${equipment?.brand}",
                "Model : " to "${equipment?.model}",
                "Building : " to "${equipment?.building}",
                "Level : " to "${equipment?.level}",
                "Domain : " to "${equipment?.domain}",
                "Local : " to "${equipment?.local}",
                "Quantity : " to "${equipment?.quantity}",
                "Status : " to "${equipment?.status}",
                "Note : " to "${equipment?.notes}"
            )
            info.map {item ->
                Row(horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.small_space))) {
                    Text(text = item.key, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                    Text(text = item.value, style = MaterialTheme.typography.bodyMedium)
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Badge(content = "${equipment?.nbFaults}")
            }
        }
    }
}