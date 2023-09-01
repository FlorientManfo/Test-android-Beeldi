package com.beeldi.beelding.data.remote

interface BeeldingApi {
    suspend fun getEquipments(): Any
    suspend fun getControlPoints(): Any
}