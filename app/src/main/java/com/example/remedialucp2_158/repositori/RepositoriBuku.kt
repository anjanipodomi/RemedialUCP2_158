package com.example.remedialucp2_158.repositori

import com.example.remedialucp2_158.room.Buku
import com.example.remedialucp2_158.room.BukuDao
import com.example.remedialucp2_158.room.Kategori
import com.example.remedialucp2_158.room.TipeBuku
import com.example.remedialucp2_158.room.TipeBukuDao
import kotlinx.coroutines.flow.Flow

interface RepositoriBuku {
    fun getAllBukuStream(): Flow<List<Buku>>
    suspend fun insertBuku(buku: Buku)
    fun getBukuStream(id: Int): Flow<Buku?>
    suspend fun updateBuku(buku: Buku)
    suspend fun softDeleteBukuById(id: Int)

    fun getAllTipeBukuStream(): Flow<List<TipeBuku>>
    suspend fun insertTipeBuku(tipeBuku: TipeBuku)
    fun getTipeBukuStream(idTipe: String): Flow<TipeBuku?>
    suspend fun updateTipeBuku(tipeBuku: TipeBuku)
    suspend fun updateStatusTipeBuku(idTipe: String, status: String)
    suspend fun softDeleteTipeBukuById(idTipe: String)

    fun getAllKategoriStream(): Flow<List<Kategori>>
    suspend fun insertKategori(kategori: Kategori)
    suspend fun updateKategori(kategori: Kategori)

    suspend fun cekCyclicKategori(idKategori: Int, parentIdBaru: Int?): Boolean

    suspend fun hapusKategori(
        idKategori: Int,
        ikutHapusBukuSecaraLogis: Boolean
    )
}

class OfflineRepositoriBuku(
    private val bukuDao: BukuDao,
    private val tipeBukuDao: TipeBukuDao
):RepositoriBuku {

    override fun getAllBukuStream(): Flow<List<Buku>> = bukuDao.getAllBuku()
    override suspend fun insertBuku(buku: Buku) = bukuDao.insert(buku)
    override fun getBukuStream(id: Int): Flow<Buku?> = bukuDao.getBuku(id)
    override suspend fun updateBuku(buku: Buku) = bukuDao.update(buku)
    override suspend fun softDeleteBukuById(id: Int) = bukuDao.softDeleteBukuById(id, System.currentTimeMillis())

    override fun getAllTipeBukuStream(): Flow<List<TipeBuku>> = tipeBukuDao.getAllTipeBuku()
    override suspend fun insertTipeBuku(tipeBuku: TipeBuku) = tipeBukuDao.insert(tipeBuku)
    override fun getTipeBukuStream(idTipe: String): Flow<TipeBuku?> = tipeBukuDao.getTipeBuku(idTipe)
    override suspend fun updateTipeBuku(tipeBuku: TipeBuku) = tipeBukuDao.update(tipeBuku)
    override suspend fun updateStatusTipeBuku(idTipe: String, status: String) = tipeBukuDao.updateStatus(idTipe, status)
    override suspend fun softDeleteTipeBukuById(idTipe: String) = tipeBukuDao.softDeleteTipeBukuById(idTipe, System.currentTimeMillis())

    override fun getAllKategoriStream(): Flow<List<Kategori>> = bukuDao.getAllKategori()
    override suspend fun insertKategori(kategori: Kategori) = bukuDao.insertKategori(kategori)
    override suspend fun updateKategori(kategori: Kategori) = bukuDao.updateKategori(kategori)

    override suspend fun cekCyclicKategori(idKategori: Int, parentIdBaru: Int?): Boolean {
        if (parentIdBaru == null) return false
        if (parentIdBaru == idKategori) return true

        var current = parentIdBaru
        while (current != null) {
            if (current == idKategori) return true
            current = bukuDao.getParentId(current)
        }
        return false
    }

    override suspend fun hapusKategori(idKategori: Int, ikutHapusBukuSecaraLogis: Boolean) =
        bukuDao.hapusKategoriDenganAturan(idKategori, ikutHapusBukuSecaraLogis)
}