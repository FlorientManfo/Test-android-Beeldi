package com.beeldi.beelding.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.beeldi.beelding.R
import com.beeldi.beelding.ui.theme.BeeldingTheme

@Composable
fun Badge(
    content: String,
    modifier: Modifier = Modifier
){
    var color = remember{
        if(content.isEmpty() || content == "0")
            Color.Green
        else Color.Red
    }

    Card(
        shape = MaterialTheme.shapes.large,
        modifier = modifier
            .wrapContentSize()
            .padding(dimensionResource(id = R.dimen.small_space)),
        elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.small_space))
    ) {
        Text(
            "$content Default(s)",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .background(color)
                .padding(dimensionResource(id = R.dimen.small_space))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BadgePreview(){
    BeeldingTheme() {
        Badge(content = "")
    }
}