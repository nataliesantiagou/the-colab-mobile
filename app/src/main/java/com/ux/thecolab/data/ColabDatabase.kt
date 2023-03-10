package com.ux.thecolab.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PatientItem::class], version = 2, exportSchema = false)
abstract class ColabDatabase : RoomDatabase() {
    abstract fun todoDao(): PatientDatabaseDao

    companion object {

        private var INSTANCE: ColabDatabase? = null

        fun getInstance(context: Context): ColabDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ColabDatabase::class.java,
                        "patient"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}