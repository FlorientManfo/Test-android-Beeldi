package com.beeldi.beelding.ui.equipment_list

sealed interface EquipmentListEvent{
    data class OnEquipmentClicked(val equipmentId: Int): EquipmentListEvent
    data class OnSearchKeywordChange(val keyword: String): EquipmentListEvent
    object ActivateSearch: EquipmentListEvent
}