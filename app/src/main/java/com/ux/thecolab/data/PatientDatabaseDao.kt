package com.ux.thecolab.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PatientDatabaseDao {
    @Query("SELECT * from patient")
    fun getAll(): LiveData<List<PatientItem>>

    @Query("SELECT * from patient where itemId = :id")
    fun getById(id: Int): PatientItem?

    @Insert
    suspend fun insert(item: PatientItem)

    @Update
    suspend fun update(item: PatientItem)

    @Delete
    suspend fun delete(item: PatientItem)

    @Query("DELETE FROM patient")
    suspend fun deleteAllPatient()
}
