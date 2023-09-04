package com.beeldi.beelding.ui.equipment_list

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.beeldi.beelding.R
import com.beeldi.beelding.ui.component.EquipmentRow
import com.beeldi.beelding.ui.component.ErrorComponent
import com.beeldi.beelding.ui.component.LoadingComponent
import com.beeldi.beelding.ui.utils.hideSoftKeyboard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EquipmentListView(
    viewModel: EquipmentListViewModel =  hiltViewModel(),
    modifier: Modifier = Modifier,
    navController: NavController
){
    val state = viewModel.state.collectAsState()

    var query by rememberSaveable {
        mutableStateOf("")
    }

    val context = LocalContext.current
    Column(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.medium_space)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.medium_space))
    ) {
        SearchBar(
            leadingIcon = {
                IconButton(onClick = {
                    viewModel.onEvent(EquipmentListEvent.OnSearchKeywordChange(query))
                    hideSoftKeyboard( context as Activity)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            },
            modifier = modifier.fillMaxWidth(),
            query = query,
            onQueryChange = {text ->
                query = text
                viewModel.onEvent(EquipmentListEvent.OnSearchKeywordChange(query))
            },
            onSearch = {query ->
                viewModel.onEvent(EquipmentListEvent.OnSearchKeywordChange(query))
                hideSoftKeyboard(context as Activity)
            },
            active = false,
            colors = SearchBarDefaults.colors(containerColor = MaterialTheme.colorScheme.tertiary),
            onActiveChange = {_ -> viewModel.onEvent(EquipmentListEvent.ActivateSearch) }
        ) {}
        when(viewModel.uiState){
            is EquipmentListUiState.Loading ->{
                LoadingComponent(message = "Equipments")
            }
            is EquipmentListUiState.Complete ->{
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.small_space))
                ){
                    itemsIndexed(
                        items = state.value,
                        key = {index, equipment -> equipment.equipmentKey}
                    ){index, equipment ->
                        EquipmentRow(
                            equipment = equipment,
                            onclickCallback = { equipmentKey -> 
                                val route = context.resources.getString(R.string.detail_route).replace(
                                    "{key}", equipmentKey
                                )
                                navController.navigate(route)
                            }
                        )
                    }
                }
            }
            is EquipmentListUiState.Error ->{
                ErrorComponent(message = (viewModel.uiState as EquipmentListUiState.Error).message)
            }
        }
    }
}