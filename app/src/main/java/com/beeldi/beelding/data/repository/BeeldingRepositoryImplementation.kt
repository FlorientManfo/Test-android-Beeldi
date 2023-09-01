package com.beeldi.beelding.data.repository

import android.util.Log
import com.beeldi.beelding.data.remote.BeeldingApi
import com.beeldi.beelding.domain.repository.BeeldingRepository
import com.beeldi.beelding.domain.utils.Resource

class BeeldingRepositoryImplementation constructor(
    private val api: BeeldingApi
): BeeldingRepository {

    override suspend fun getEquipments(): Resource<Any> {
        return try{
            Resource.Success(
                data = api.getControlPoints()
            )
        } catch (e: Exception){
            e.printStackTrace()
            Log.e("Exception", "${e.message}")
            Resource.Error(e.message?:"An unknown error occurred.")
        }
    }

    override suspend fun getControlPoints(): Resource<Any> {
        return try {
            Resource.Success(
                data = api.getEquipments()
            )
        }catch (e: Exception){
            e.printStackTrace()
            Log.e("Exception", "${e.message}")
            Resource.Error(e.message?:"An unknown error occurred.")
        }
    }
}