package com.beeldi.beelding.ui.equipment_detail

data class EquipmentListState(
    val allEquipments: List<Any>,
    val filteredEquipments: List<Any>,
    val loading: Boolean = false
)
