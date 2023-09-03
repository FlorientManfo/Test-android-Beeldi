package com.beeldi.beelding.ui.equipment_list

sealed interface EquipmentListEvent{
    data class OnEquipmentClicked(val equipmentId: Int)
    data class OnSubmitSearch(val keyword: String)
    data class OnClearingSearchBar(val content: String)
}