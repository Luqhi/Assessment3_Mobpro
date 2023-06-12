package org.d3if0067.mobpro1.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0067.mobpro1.db.TiketDao
import org.d3if0067.mobpro1.db.TiketEntity
import org.d3if0067.mobpro1.model.HasilTiket
import org.d3if0067.mobpro1.model.KategoriTiket
import org.d3if0067.mobpro1.model.hitungTiket

class HitungViewModel (private val db: TiketDao) : ViewModel() {
    private val hasilTiket = MutableLiveData<HasilTiket?>()

    fun hitungTiket(jumlah: Float, isCat1: Boolean) {
        val dataTiket = TiketEntity(
            jumlah = jumlah,
            isCat1 = isCat1
        )
        hasilTiket.value = dataTiket.hitungTiket()

        viewModelScope.launch{
            withContext(Dispatchers.IO) {
                db.insert(dataTiket)
            }
        }
    }

    fun getHasilTiket(): LiveData<HasilTiket?> = hasilTiket
}