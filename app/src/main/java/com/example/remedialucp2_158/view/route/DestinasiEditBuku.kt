package com.example.remedialucp2_158.view.route

import com.example.remedialucp2_158.R

object DestinasiEditBuku {
    override val route = "item_edit"
    override val titleRes = R.string.edit_buku

    const val itemIdArg = "idBuku"
    val routeWithArgs = "$route/{$itemIdArg}"
}