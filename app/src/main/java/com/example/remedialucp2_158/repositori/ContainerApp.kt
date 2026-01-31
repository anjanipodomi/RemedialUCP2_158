package com.example.remedialucp2_158.repositori


import android.app.Application
import android.content.Context
import com.example.remedialucp2_158.room.DatabaseBuku

interface ContainerApp{
    val repositoriBuku : RepositoriBuku
}

class ContainerDataApp(private val context: Context):
    ContainerApp{
    override val repositoriBuku: RepositoriBuku by lazy {
        OfflineRepositoriBuku(
            bukuDao = DatabaseBuku.getDatabase(context).bukuDao(),
            tipeBukuDao = DatabaseBuku.getDatabase(context).tipeBukuDao())
    }
}

class AplikasiBuku : Application() {
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(context = this)
    }
}