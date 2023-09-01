package com.beeldi.beelding.di

import com.beeldi.beelding.data.remote.BeeldingApi
import com.beeldi.beelding.data.repository.BeeldingRepositoryImplementation
import com.beeldi.beelding.domain.repository.BeeldingRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app

interface Container {
    val repository: BeeldingRepository
}

class ContainerImplementation: Container{

    //Implement the API interface
    private val api = object: BeeldingApi{
        override suspend fun getEquipments(): Any {
            TODO("Not yet implemented")
        }

        override suspend fun getControlPoints(): Any {
            TODO("Not yet implemented")
        }

    }

    //Create repository instance from API implementation
    override val repository: BeeldingRepository by lazy{
        BeeldingRepositoryImplementation(api)
    }
}