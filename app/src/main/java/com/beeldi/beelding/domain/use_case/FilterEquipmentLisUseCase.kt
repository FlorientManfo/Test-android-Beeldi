package com.beeldi.beelding.domain.use_case

import com.beeldi.beelding.domain.model.Equipment
import kotlin.math.max

class FilterEquipmentLisUseCase {
    operator fun invoke(
        equipmentByCheckpoint: List<Equipment>,
        keyword: String
    ): List<Equipment> {
        var nameMatchingCount = 0
        var domainMatchingCount = 0

        //FFind the property whose value is closest to the keyword
        equipmentByCheckpoint.map {
            if (it.name.lowercase().contains(keyword.lowercase())){
                nameMatchingCount += 1
            }
            if (it.domain.lowercase().contains(keyword.lowercase())){
                domainMatchingCount += 1
            }
        }

        return when (max(nameMatchingCount, domainMatchingCount)){
            domainMatchingCount -> equipmentByCheckpoint
                .filter { it.domain.lowercase().contains(keyword.lowercase()) }
                .sortedBy { it.domain }
            else -> equipmentByCheckpoint
                .filter { it.name.lowercase().contains(keyword.lowercase()) }
                .sortedBy { it.name }
        }
    }
}