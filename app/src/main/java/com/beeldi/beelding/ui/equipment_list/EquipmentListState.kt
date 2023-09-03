package com.beeldi.beelding.ui.equipment_list

import com.beeldi.beelding.domain.model.Equipment

data class EquipmentListState(
    val allEquipments: List<Equipment> = emptyList(),
    val filteredEquipments: List<Equipment> = emptyList(),
    val loading: Boolean = false
)
