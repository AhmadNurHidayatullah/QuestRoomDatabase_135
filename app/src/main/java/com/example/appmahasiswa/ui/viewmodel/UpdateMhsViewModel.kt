package com.example.appmahasiswa.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmahasiswa.data.entity.Mahasiswa
import com.example.appmahasiswa.repository.RepositoryMhs
import com.example.appmahasiswa.ui.navigation.DestinasiUpdate
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateMhsViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMhs: RepositoryMhs
) : ViewModel() {

    var updateUiState by mutableStateOf(MhsUiState())
        private set

    private val _nim: String = checkNotNull(savedStateHandle[DestinasiUpdate.NIM])

    init {
        viewModelScope.launch {
            updateUiState = repositoryMhs.getMhs(_nim)
                .filterNotNull()
                .first()
                .toUiStateMhs()
        }
    }

    fun updateState(mahasiswaEvent: MahasiswaEvent) {
        updateUiState = updateUiState.copy(
            mahasiswaEvent = mahasiswaEvent,
        )
    }

    fun validateFields(): Boolean {
        val event = updateUiState.mahasiswaEvent
        val errorState = FormErrorState(
            nim = if (event.nim.isNotEmpty()) null else "NIM Tidak Boleh Kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama Tidak Boleh Kosong",
            jenisKelamin = if (event.jenisKelamin.isNotEmpty()) null else "Jenis Kelamin Tidak Boleh Kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "Alamat Tidak Boleh Kosong",
            kelas = if (event.kelas.isNotEmpty()) null else "Kelas Tidak Boleh Kosong",
            angkatan = if (event.angkatan.isNotEmpty()) null else "Angkatan Tidak Boleh Kosong"
        )

        updateUiState = updateUiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun updateData() {
        val currentEvent = updateUiState.mahasiswaEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryMhs.updateMhs(currentEvent.toMahasiswaEntity())
                    updateUiState = updateUiState.copy(
                        snackbarMessage = "Data Berhasil Diupdate",
                        mahasiswaEvent = MahasiswaEvent(),
                        isEntryValid = FormErrorState()
                    )
                    println("snackBarMessage diatur: ${updateUiState.
                    snackbarMessage}")
                } catch (e: Exception) {
                    updateUiState = updateUiState.copy(
                        snackbarMessage = "Data Gagal Diupdate"
                    )
                }
            }
        } else {
            updateUiState = updateUiState.copy(
                snackbarMessage = "Data Gagal Diupdate"
            )
        }
    }

    fun resetSnackBarMessage() {
        updateUiState = updateUiState.copy(snackbarMessage = null)
    }
}

fun Mahasiswa.toUiStateMhs(): MhsUiState = MhsUiState(
    mahasiswaEvent = this.toDetailUiEvent()
)