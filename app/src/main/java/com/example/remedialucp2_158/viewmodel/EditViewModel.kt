package com.example.remedialucp2_158.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remedialucp2_158.repositori.RepositoriBuku
import com.example.remedialucp2_158.view.route.DestinasiDetailBuku
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriBuku: RepositoriBuku
) : ViewModel() {

    var uiStateBuku by mutableStateOf(UIStateBuku())
        private set

    private val idBuku: Int =
        checkNotNull(savedStateHandle[DestinasiDetailBuku.itemIdArg])

    init {
        viewModelScope.launch {
            uiStateBuku = repositoriBuku.getBukuStream(idBuku)
                .filterNotNull()
                .first()
                .toUIStateBuku(true)
        }
    }

    fun updateUiState(detailBuku: DetailBuku) {
        uiStateBuku = uiStateBuku.copy(
            detailBuku = detailBuku,
            isEntryValid = validasiInput(detailBuku)
        )
    }

    private fun validasiInput(detailBuku: DetailBuku): Boolean =
        detailBuku.judul.isNotBlank() &&
                detailBuku.isbn.isNotBlank() &&
                detailBuku.tahun.isNotBlank()

    suspend fun updateBuku() {
        if (validasiInput(uiStateBuku.detailBuku)) {
            repositoriBuku.updateBuku(uiStateBuku.detailBuku.toBuku())
        }
    }
}