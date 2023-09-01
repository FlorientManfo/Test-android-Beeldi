package com.beeldi.beelding.ui.equipment_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.beeldi.beelding.BeeldingApp
import com.beeldi.beelding.domain.repository.BeeldingRepository
import kotlinx.coroutines.launch

class EquipmentListViewModel(
    private val repository: BeeldingRepository
): ViewModel(){
    init {
        getAllEquipments()
    }

    private fun getAllEquipments(){
        TODO("Not yet implemented")
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