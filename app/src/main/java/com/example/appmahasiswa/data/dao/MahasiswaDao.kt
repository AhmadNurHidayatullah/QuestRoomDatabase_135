package com.example.appmahasiswa.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.appmahasiswa.data.entity.Mahasiswa
import kotlinx.coroutines.flow.Flow


@Dao
interface MahasiswaDao {
    @Query("select * from mahasiswa")
    fun getAllMahasiswa() : Flow<List<Mahasiswa>>

    @Query("SELECT * FROM mahasiswa WHERE nim = :nim")
    fun getMahasiswa(nim: String): Flow<Mahasiswa>

    @Insert
    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)

    @Update
    suspend fun updateMahasiswa(mahasiswa: Mahasiswa)

    @Delete
    suspend fun deleteMahasiswa(mahasiswa: Mahasiswa)
}