package com.beeldi.beelding.ui.equipment_detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.beeldi.beelding.BeeldingApp
import com.beeldi.beelding.domain.repository.BeeldingRepository
import com.beeldi.beelding.domain.utils.Resource
import com.beeldi.beelding.ui.equipment_list.EquipmentListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: BeeldingRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    val equipmentKey = savedStateHandle.get<String>("key")?:""

    private val TAG = "DetailViewModel"
    private val _state = MutableStateFlow<DetailState?>(DetailState())
    var state = _state.asStateFlow()
        private set

    var uiState: DetailUIState by mutableStateOf(
        DetailUIState.Loading
    )
        private set

    init {
        getEquipment()
        getCheckpoints()
    }

    private fun getEquipment(){
        viewModelScope.launch {
            repository.getEquipmentByKey(equipmentKey = equipmentKey) { resource ->
                uiState = try {
                    when (resource) {
                        is Resource.Error -> {
                            DetailUIState.Error("An unexpected error occurred during retraiving equipment ")
                        }
                        is Resource.Success -> {
                            _state.update {
                                resource.data?.let { it1 ->
                                    it?.copy(
                                        equipment = it1
                                    )
                                }
                            }
                            DetailUIState.Complete
                        }
                    }
                } catch (e: Exception){
                    Log.e(TAG, e.message?: "An error occurred while updating state")
                    DetailUIState.Error("An error occurred while updating state")
                }
            }
        }
    }

    private fun getCheckpoints(){
        viewModelScope.launch {
            repository.getCheckpoints(equipmentKey = equipmentKey) { resource ->
                try{
                    uiState =  when (resource) {
                        is Resource.Error ->{
                            DetailUIState.Error("An unexpected error occurred during loading of equipments ")
                        }
                        is Resource.Success -> {
                            _state.update {
                                resource.data?.let { it1 ->
                                    it?.copy(
                                        checkpoint = it1
                                    )
                                }
                            }
                            DetailUIState.Complete
                        }
                    }
                } catch (e: Exception){
                    Log.e(TAG, e.message?: "An error occurred while updating state")
                }
            }
        }
    }
}