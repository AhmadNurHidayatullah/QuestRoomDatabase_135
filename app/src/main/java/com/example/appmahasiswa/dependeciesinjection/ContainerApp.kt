package com.example.appmahasiswa.dependeciesinjection

import android.content.Context
import com.example.appmahasiswa.data.database.KrsDatabase
import com.example.appmahasiswa.repository.LocalRepositoryMhs
import com.example.appmahasiswa.repository.RepositoryMhs

interface InterfaceContainerApp{
    val repositoryMhs: RepositoryMhs
}

class ContainerApp(private val context: Context): InterfaceContainerApp{
    override val repositoryMhs: RepositoryMhs by lazy {
        LocalRepositoryMhs(KrsDatabase.getDatabase(context).mahasiswaDao())
    }
}