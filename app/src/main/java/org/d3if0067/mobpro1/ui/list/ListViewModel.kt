package org.d3if0067.mobpro1.ui.list

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if0067.mobpro1.data.Tiket
import org.d3if0067.mobpro1.network.ApiStatus
import org.d3if0067.mobpro1.network.TiketApi
import org.d3if0067.mobpro1.network.UpdateWorker
import java.util.concurrent.TimeUnit

class ListViewModel : ViewModel(){
    private val data = MutableLiveData<List<Tiket>>()
    private val status = MutableLiveData<ApiStatus>()

    init {
        retrieveData()
    }

    private fun retrieveData(){
        viewModelScope.launch (Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)
            try {
                data.postValue(TiketApi.service.getTiket())
                status.postValue(ApiStatus.SUCCESS)
            }catch (e: Exception){
                Log.d("ListViewModel", "Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
            }
        }
    }

    fun getData(): LiveData<List<Tiket>> = data

    fun getStatus(): LiveData<ApiStatus> = status

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            UpdateWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
}