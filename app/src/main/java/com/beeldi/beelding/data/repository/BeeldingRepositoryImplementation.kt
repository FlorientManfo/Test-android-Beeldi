package com.beeldi.beelding.data.repository

import android.util.Log
import com.beeldi.beelding.domain.model.Checkpoint
import com.beeldi.beelding.domain.model.Equipment
import com.beeldi.beelding.domain.repository.BeeldingRepository
import com.beeldi.beelding.domain.utils.Resource
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class BeeldingRepositoryImplementation: BeeldingRepository {
    private val TAG = "Repository"
    private val db = Firebase.database
    private val equipmentsReference = db.getReference("Equipments")
    private val checkpointsReference = db.getReference("Checkpoints")

    override suspend fun getEquipments(callback: (Resource<List<Equipment>>) -> Unit) {
        equipmentsReference.addListenerForSingleValueEvent(
            object: ValueEventListener {
                val equipments = mutableListOf<Equipment>()
                override fun onDataChange(snapshot: DataSnapshot) {
                    val resource: Resource<List<Equipment>> = try{
                        for(ds in snapshot.children){
                            ds.getValue(Equipment::class.java)?.let {
                                ds.key?.let{
                                    key ->  it.equipmentKey = key
                                }
                                equipments.add(it)
                            }
                        }
                        Resource.Success(
                            data = equipments
                        )
                    } catch (e: Exception){
                        e.printStackTrace()
                        Log.e(TAG, "${e.message} ${snapshot.toString()}")
                        Resource.Error(e.message?:"An unknown error occurred.")
                    }
                    callback(resource)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d(TAG, error.message)
                    val resource = Resource.Error<List<Equipment>>(error.message)
                    callback(resource)
                }

            }
        )
    }

    override suspend fun getEquipmentByKey(
        equipmentKey: String,
        callback: (Resource<Equipment>) -> Unit
    ) {
        equipmentsReference.addListenerForSingleValueEvent(
            object: ValueEventListener {
                lateinit var equipment: Equipment
                override fun onDataChange(snapshot: DataSnapshot) {
                    val resource:Resource<Equipment> = try{
                        for(ds in snapshot.children){
                            if (ds.key == equipmentKey){
                                ds.getValue(Equipment::class.java)?.let {
                                    equipment = it
                                }
                            }
                        }
                        Resource.Success(
                            data = equipment
                        )
                    } catch (e: Exception){
                        e.printStackTrace()
                        Log.e(TAG, "${e.message} ${snapshot.toString()}")
                        Resource.Error(e.message?:"An unknown error occurred.")
                    }
                    callback(resource)
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.d(TAG, error.message)
                    val resource = Resource.Error<Equipment>(error.message)
                    callback(resource)
                }
            }
        )
    }

    override suspend fun getCheckpoints(equipmentKey: String,
                                        callback: (Resource<List<Checkpoint>>) -> Unit) {
        checkpointsReference.addListenerForSingleValueEvent(
            object: ValueEventListener {
                val checkpoints = mutableListOf<Checkpoint>()
                override fun onDataChange(snapshot: DataSnapshot) {
                    val resource:Resource<List<Checkpoint>> = try{
                        for(ds in snapshot.children){
                            ds.getValue(Checkpoint::class.java)?.let {
                                if(it.equipmentKey == equipmentKey){
                                    checkpoints.add(it)
                                }
                            }
                        }
                        Resource.Success(
                            data = checkpoints
                        )
                    } catch (e: Exception){
                        e.printStackTrace()
                        Log.e(TAG, "${e.message} ${snapshot.toString()}")
                        Resource.Error(e.message?:"An unknown error occurred.")
                    }
                    callback(resource)
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.d(TAG, error.message)
                    val resource = Resource.Error<List<Checkpoint>>(error.message)
                    callback(resource)
                }
            }
        )
    }
}