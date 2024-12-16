package com.example.appmahasiswa.ui.viewmodel


import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.appmahasiswa.KrsApp


object PenyediaViewModel {

    val Factory = viewModelFactory {
        initializer {
            MahasiswaViewModel(
                KrsApp().containerApp.repositoryMhs
            )
        }
    }
}

fun CreationExtras.KrsApp(): KrsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KrsApp)