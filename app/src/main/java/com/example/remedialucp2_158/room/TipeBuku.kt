package com.example.remedialucp2_158.room



@Entity(tableName = "tblTipeBuku")
data class TipeBuku(
    @PrimaryKey
    val idTipe : String,
    val idBuku : Int,
    val status : String = "TERSEDIA"
)