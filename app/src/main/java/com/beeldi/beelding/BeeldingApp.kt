package com.beeldi.beelding

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//This will help us to wrap an instance of repository during application creation
//And we will be able to share it inside all the class of application
@HiltAndroidApp
class BeeldingApp: Application()
