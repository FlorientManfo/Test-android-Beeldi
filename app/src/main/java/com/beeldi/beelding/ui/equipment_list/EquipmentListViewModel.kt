package com.beeldi.beelding.ui.equipment_list

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beeldi.beelding.domain.model.Equipment
import com.beeldi.beelding.domain.repository.BeeldingRepository
import com.beeldi.beelding.domain.use_case.FilterEquipmentListUseCase
import com.beeldi.beelding.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EquipmentListViewModel @Inject constructor(
    private val repository: BeeldingRepository
): ViewModel(){

    private val TAG = "ListViewModel"
    private val _state = MutableStateFlow(emptyList<Equipment>())
    var state = _state.asStateFlow()
        private set

    var uiState: EquipmentListUiState by mutableStateOf(
        EquipmentListUiState.Loading
    )
        private set

    init {
        getAllEquipments()
    }

    private fun getAllEquipments(){
        viewModelScope.launch {
            repository.getEquipments { resource ->
                try{
                   uiState =  when (resource) {
                        is Resource.Error ->{
                            EquipmentListUiState.Error("An unexpected error occurred during loading of equipments ")
                        }
                        is Resource.Success -> {
                            val fuc = FilterEquipmentListUseCase()
                            _state.update {
                                fuc.invoke(resource.data?: emptyList(), "")
                            }
                            EquipmentListUiState.Complete
                        }
                    }
                } catch (e: Exception){
                    Log.e(TAG, e.message?: "An error occurred while updating state")
                }
            }
        }
    }

    fun onEvent(event: EquipmentListEvent) {
        when(event){
            is EquipmentListEvent.OnEquipmentClicked ->{
                TODO()
            }
            is EquipmentListEvent.OnSearchKeywordChange ->{
                uiState = EquipmentListUiState.Loading
                if(event.keyword.isNotEmpty()){
                    val fuc = FilterEquipmentListUseCase()
                    _state.update {
                        fuc.invoke(_state.value, event.keyword)
                    }
                    uiState = EquipmentListUiState.Complete
                }else{
                    getAllEquipments()
                }
            }
            is EquipmentListEvent.ActivateSearch ->{
                getAllEquipments()
            }
        }
    }
}