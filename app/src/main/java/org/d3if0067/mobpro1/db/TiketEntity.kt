package org.d3if0067.mobpro1.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tiket")
data class TiketEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var jumlah: Float,
    var isCat1: Boolean
)
