package org.d3if0067.mobpro1.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TiketEntity::class], version = 1, exportSchema = false)
abstract class TiketDb: RoomDatabase(){
    abstract val dao: TiketDao

    companion object {
        @Volatile
        private var INSTANCE: TiketDb? = null

        fun getInstance(context: Context): TiketDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TiketDb::class.java,
                        "tiket.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}