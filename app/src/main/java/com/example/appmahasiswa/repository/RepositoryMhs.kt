package com.example.appmahasiswa.repository

import com.example.appmahasiswa.data.entity.Mahasiswa
import kotlinx.coroutines.flow.Flow

interface RepositoryMhs {
    suspend fun insertMhs(mahasiswa: Mahasiswa)
    fun getAllMahasiswa(): Flow<List<Mahasiswa>>

    fun getMhs(nim: String): Flow<Mahasiswa>


    suspend fun deleteMhs(mahasiswa: Mahasiswa)


    suspend fun updateMhs(mahasiswa: Mahasiswa)
}