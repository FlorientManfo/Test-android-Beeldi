package com.beeldi.beelding.ui.equipment_detail

sealed interface DetailUIState{
    object Complete : DetailUIState
    object Loading : DetailUIState
    data class Error(val message: String) : DetailUIState
}