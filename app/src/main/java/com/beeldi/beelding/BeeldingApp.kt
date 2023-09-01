package com.beeldi.beelding

import android.app.Application
import com.beeldi.beelding.di.Container
import com.beeldi.beelding.di.ContainerImplementation

//This will help us to wrap an instance of repository during application creation
//And we will be able to share it inside all the class of application
class BeeldingApp: Application(){
    lateinit var container: Container
    override fun onCreate() {
        super.onCreate()
        container = ContainerImplementation()
    }
}