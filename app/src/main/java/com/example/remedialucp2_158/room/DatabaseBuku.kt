package com.example.remedialucp2_158.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        Buku::class,
        TipeBuku::class,
        Kategori::class,
        Pengarang::class,
        BukuPengarang::class,
        AuditLog::class
    ],
    version = 1,
    exportSchema = false
)
abstract class DatabaseBuku : RoomDatabase() {

    abstract fun bukuDao(): BukuDao
    abstract fun tipeBukuDao(): TipeBukuDao

    companion object {
        @Volatile
        private var Instance: DatabaseBuku? = null

        fun getDatabase(context: Context): DatabaseBuku {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, DatabaseBuku::class.java, "dbBuku")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}