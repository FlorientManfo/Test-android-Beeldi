package com.beeldi.beelding.ui.equipment_list

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.beeldi.beelding.BeeldingApp
import com.beeldi.beelding.domain.model.Equipment
import com.beeldi.beelding.domain.repository.BeeldingRepository
import com.beeldi.beelding.domain.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EquipmentListViewModel(
    private val repository: BeeldingRepository
): ViewModel(){

    private val TAG = "ListViewModel"
    private val _state = MutableStateFlow(EquipmentListState())
    var state = _state.asStateFlow()
        private set

    init {
        getAllEquipments()
    }

    private fun getAllEquipments(){
        viewModelScope.launch {
            repository.getEquipments { resource ->
                try{
                    when (resource) {
                        is Resource.Error ->{

                        }
                        is Resource.Success -> {
                            _state.update {
                                it.copy(
                                    allEquipments = resource.data?: emptyList()
                                )
                            }
                        }
                    }
                } catch (e: Exception){
                    Log.e(TAG, e.message?: "An error occurred while updating state")
                }
            }
        }
    }

    /*This companion object provide us the possibility to ge and instance of
    * repository that will be injected inside the view-model and will
    * be available during the hole live of our application*/
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as BeeldingApp
                val repository = application.container.repository
                EquipmentListViewModel(
                    repository = repository,
                )
            }
        }
    }
}