package com.example.remedialucp2_158.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
interface BukuDao {
    @Query(value= "SELECT * from tblBuku WHERE isDeleted = 0 ORDER BY judul ASC")
    fun getAllBuku(): Flow<List<Buku>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(buku: Buku)

    @Query("SELECT * from tblBuku WHERE id = :id AND isDeleted = 0")
    fun getBuku(id: Int): Flow<Buku?>

    @Update
    suspend fun update(buku: Buku)

    @Query("UPDATE tblBuku SET isDeleted = 1, deletedAt = :waktu WHERE id = :id")
    suspend fun softDeleteBukuById(id: Int, waktu: Long)

    @Query("UPDATE tblBuku SET idKategori = NULL WHERE idKategori = :idKategori")
    suspend fun setBukuTanpaKategori(idKategori: Int)

    @Query("SELECT COUNT(*) FROM tblTipeBuku WHERE idBuku IN (SELECT id FROM tblBuku WHERE idKategori IN (:kategoriIds)) AND status = 'DIPINJAM' AND isDeleted = 0")
    suspend fun countTipeBukuDipinjamByKategoriIds(kategoriIds: List<Int>): Int

    @Query("SELECT * from tblKategori WHERE isDeleted = 0 ORDER BY namaKategori ASC")
    fun getAllKategori(): Flow<List<Kategori>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertKategori(kategori: Kategori)

    @Update
    suspend fun updateKategori(kategori: Kategori)

    @Query("SELECT * FROM tblKategori WHERE idKategori = :id AND isDeleted = 0")
    suspend fun getKategoriById(id: Int): Kategori?

}