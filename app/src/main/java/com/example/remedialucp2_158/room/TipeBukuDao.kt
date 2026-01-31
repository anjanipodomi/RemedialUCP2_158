package com.example.remedialucp2_158.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
interface TipeBukuDao {
    @Query(value= "SELECT * from tblTipeBuku WHERE isDeleted = 0 ORDER BY idTipe ASC")
    fun getAllTipeBuku(): Flow<List<TipeBuku>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tipeBuku: TipeBuku)

    @Query("SELECT * from tblTipeBuku WHERE idTipe = :idTipe AND isDeleted = 0")
    fun getTipeBuku(idTipe: String): Flow<TipeBuku?>

    @Update
    suspend fun update(tipeBuku: TipeBuku)

    @Query("UPDATE tblTipeBuku SET isDeleted = 1, deletedAt = :waktu WHERE idTipe = :idTipe")
    suspend fun softDeleteTipeBukuById(idTipe: String, waktu: Long)

    @Query("UPDATE tblTipeBuku SET status = :status WHERE idTipe = :idTipe AND isDeleted = 0")
    suspend fun updateStatus(idTipe: String, status: String)
}