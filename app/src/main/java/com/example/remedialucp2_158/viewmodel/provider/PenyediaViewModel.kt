package com.example.remedialucp2_158.viewmodel.provider

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.remedialucp2_158.repositori.AplikasiBuku
import com.example.remedialucp2_158.viewmodel.DetailViewModel
import com.example.remedialucp2_158.viewmodel.EditViewModel
import com.example.remedialucp2_158.viewmodel.EntryViewModel
import com.example.remedialucp2_158.viewmodel.HomeViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(aplikasiBuku().container.repositoriBuku)
        }
        initializer {
            EntryViewModel(aplikasiBuku().container.repositoriBuku)
        }
        initializer {
            DetailViewModel(
                this.createSavedStateHandle(),
                aplikasiBuku().container.repositoriBuku)
        }
        initializer {
            EditViewModel(
                this.createSavedStateHandle(),
                aplikasiBuku().container.repositoriBuku)
        }
    }
}

fun CreationExtras.aplikasiBuku(): AplikasiBuku =