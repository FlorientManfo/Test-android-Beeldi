package com.beeldi.beelding.domain.repository
import com.beeldi.beelding.domain.model.Checkpoint
import com.beeldi.beelding.domain.model.Equipment
import com.beeldi.beelding.domain.utils.Resource

interface BeeldingRepository {
    suspend fun getEquipments(callback: (Resource<List<Equipment>>) -> Unit)
    suspend fun getEquipmentByKey(equipmentKey: String, callback: (Resource<Equipment>) -> Unit)
    suspend fun getCheckpoints(equipmentKey: String, callback: (Resource<List<Checkpoint>>) -> Unit)
}