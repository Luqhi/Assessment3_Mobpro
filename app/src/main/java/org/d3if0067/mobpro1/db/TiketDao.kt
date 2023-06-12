package org.d3if0067.mobpro1.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TiketDao {
    @Insert
    fun insert(tiket: TiketEntity)

    @Query("SELECT * FROM tiket ORDER BY id DESC")
    fun getLastTiket(): LiveData<List<TiketEntity>>

    @Query("DELETE FROM tiket")
    fun clearData()
}