package com.example.remedialucp2_158.view.route

import com.example.remedialucp2_158.R

object DestinasiDetailBuku {
    override val route = "detail_buku"
    override val titleRes = R.string.Detail_buku
    const val itemIdArg = "idBuku"
    val routeWithArgs = "$route/{$itemIdArg}"
}