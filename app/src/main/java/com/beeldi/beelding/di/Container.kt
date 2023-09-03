package com.beeldi.beelding.di

import com.beeldi.beelding.data.repository.BeeldingRepositoryImplementation
import com.beeldi.beelding.domain.repository.BeeldingRepository

interface Container {
    val repository: BeeldingRepository
}

class ContainerImplementation: Container{

    //Create repository instance that will be wrapped in the application
    override val repository: BeeldingRepository by lazy{
        BeeldingRepositoryImplementation()
    }
}