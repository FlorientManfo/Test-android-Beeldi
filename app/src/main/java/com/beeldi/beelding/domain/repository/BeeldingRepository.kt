package com.beeldi.beelding.domain.repository

import com.beeldi.beelding.domain.utils.Resource

interface BeeldingRepository {
    suspend fun getEquipments(): Resource<Any>
    suspend fun getControlPoints(): Resource<Any>
}