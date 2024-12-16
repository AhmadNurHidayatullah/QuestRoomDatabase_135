package com.example.appmahasiswa

import android.app.Application
import com.example.appmahasiswa.dependeciesinjection.ContainerApp

class KrsApp : Application() {
    // fungsinya untuk menyimpan instance ContainerApp
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        //membuat instance ContainerApp
        containerApp = ContainerApp(this)
        // instance adalah object yang dibuat dari class
    }
}