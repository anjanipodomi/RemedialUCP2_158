package com.example.remedialucp2_158.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.remedialucp2_158.repositori.RepositoriBuku
import com.example.remedialucp2_158.room.Buku

class EntryViewModel(private val repositoriBuku: RepositoriBuku) : ViewModel() {
    var uiStateBuku by mutableStateOf(UIStateBuku())
        private set


    private fun validasiInput(uiState: DetailBuku = uiStateBuku.detailBuku): Boolean {
        return with(uiState) {
            judul.isNotBlank() && isbn.isNotBlank() && tahun.isNotBlank()
        }
    }

    fun updateUIState(detailBuku: DetailBuku) {
        uiStateBuku =
            UIStateBuku(
                detailBuku = detailBuku,
                isEntryValid = validasiInput(detailBuku)
            )
    }

    suspend fun saveBuku() {
        if (validasiInput()) {
            repositoriBuku.insertBuku(uiStateBuku.detailBuku.toBuku())
        }
    }
}


data class UIStateBuku(
    val detailBuku: DetailBuku = DetailBuku(),
    val isEntryValid: Boolean = false
)

data class DetailBuku(
    val id: Int = 0,
    val judul: String = "",
    val isbn: String = "",
    val tahun: String = ""
)

fun DetailBuku.toBuku(): Buku = Buku(
    id = id,
    judul = judul,
    isbn = isbn,
    tahun = tahun
)

fun Buku.toUIStateBuku(isEntryValid: Boolean = false): UIStateBuku =
    UIStateBuku(
        detailBuku = this.toDetailBuku(),
        isEntryValid = isEntryValid
    )

fun Buku.toDetailBuku(): DetailBuku = DetailBuku(
    id = id,
    judul = judul,
    isbn = isbn,
    tahun = tahun
)