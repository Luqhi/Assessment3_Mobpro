package org.d3if0067.mobpro1.model

import org.d3if0067.mobpro1.db.TiketEntity

fun TiketEntity.hitungTiket(): HasilTiket {
    val hargaCat1 = 5000000
    val hargaCat2 = 4000000

    val total = if (isCat1) {
        jumlah * hargaCat1
    }else {
        jumlah * hargaCat2
    }

    val kategoriTiket = if (isCat1) {
        KategoriTiket.BAIK
    }else {
        KategoriTiket.BURUK
    }
    return HasilTiket(total, kategoriTiket)
}