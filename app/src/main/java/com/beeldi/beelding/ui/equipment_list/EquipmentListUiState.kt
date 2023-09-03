package com.beeldi.beelding.ui.equipment_list

sealed interface EquipmentListUiState {
    object Complete : EquipmentListUiState
    object Loading : EquipmentListUiState
    data class Error(val message: String) : EquipmentListUiState
}
