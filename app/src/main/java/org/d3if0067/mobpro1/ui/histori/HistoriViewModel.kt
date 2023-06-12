package org.d3if0067.mobpro1.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0067.mobpro1.db.TiketDao

class HistoriViewModel(private val db: TiketDao) : ViewModel() {
    val data = db.getLastTiket()

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }
}