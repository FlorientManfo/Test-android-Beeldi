package com.beeldi.beelding.ui.equipment_detail

import com.beeldi.beelding.domain.model.Checkpoint
import com.beeldi.beelding.domain.model.Equipment

data class DetailState(
    val equipment: Equipment = Equipment(),
    val checkpoint: List<Checkpoint> = emptyList()
)