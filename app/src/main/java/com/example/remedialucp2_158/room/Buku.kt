package com.example.remedialucp2_158.room

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "tblBuku")
data class Buku(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val judul : String,
    val isbn : String,
    val tahun : String,
    val idKategori : Int? = null,
    val isDeleted : Boolean = false,
    val deletedAt : Long? = null
)

@Entity(tableName = "tblKategori")
data class Kategori(
    @PrimaryKey(autoGenerate = true)
    val idKategori : Int = 0,
    val namaKategori : String,
    val parentId : Int? = null,
    val isDeleted : Boolean = false,
    val deletedAt : Long? = null
)

@Entity(tableName = "tblPengarang")
data class Pengarang(
    @PrimaryKey(autoGenerate = true)
    val idPengarang : Int = 0,
    val namaPengarang : String,
    val isDeleted : Boolean = false,
    val deletedAt : Long? = null
)

@Entity(tableName = "tblBukuPengarang", primaryKeys = ["idBuku","idPengarang"])
data class BukuPengarang(
    val idBuku : Int,
    val idPengarang : Int
)

@Entity(tableName = "tblAuditLog")
data class AuditLog(
    @PrimaryKey(autoGenerate = true)
    val idAudit : Int = 0,
    val entitas : String,
    val idEntitas : String,
    val aksi : String,
    val sebelum : String? = null,
    val sesudah : String? = null,
    val waktu : Long = System.currentTimeMillis()
)