package com.beeldi.beelding.ui.equipment_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.beeldi.beelding.R
import com.beeldi.beelding.ui.component.CheckpointSection
import com.beeldi.beelding.ui.component.EquipmentImage
import com.beeldi.beelding.ui.component.ErrorComponent
import com.beeldi.beelding.ui.component.InfoSection
import com.beeldi.beelding.ui.component.LoadingComponent
import com.beeldi.beelding.ui.equipment_list.EquipmentListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EquipmentDetailView(
    viewModel: DetailViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    equipmentKey: String,
    onBackToList: () -> Unit
){
    val state = viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Detail",
                        color = MaterialTheme.colorScheme.secondary
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                navigationIcon = {
                    IconButton(
                        onClick = onBackToList) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back to equipment",
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            )
        },
    ) {
        when(viewModel.uiState){
            is DetailUIState.Loading ->{
                LoadingComponent(message = "${state.value?.equipment?.name}")
            }
            is DetailUIState.Complete ->{
                Column(
                    modifier = modifier
                        .padding(it)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.small_space)),
                    horizontalAlignment = Alignment.Start
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(1 / 2f)
                    ) {
                        EquipmentImage(data = "${state.value?.equipment?.photo}")
                    }
                    InfoSection(equipment = state.value?.equipment)
                    Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.medium_space)))
                    Text(
                        text = "Checkpoints",
                        style = MaterialTheme.typography.titleLarge,
                        overflow = TextOverflow.Clip
                    )
                    CheckpointSection(checkpoints = state.value?.checkpoint?: emptyList())
                }
            }
            is DetailUIState.Error -> {
                ErrorComponent(message = (viewModel.uiState as DetailUIState.Error).message)
            }
        }
    }

}