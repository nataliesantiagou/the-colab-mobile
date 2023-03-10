package com.ux.thecolab.data

import androidx.lifecycle.LiveData

class PatientRepository(private val patientDatabaseDao: PatientDatabaseDao) {

    val readAllData: LiveData<List<PatientItem>> = patientDatabaseDao.getAll()

    suspend fun addTodo(todoItem: PatientItem) {
        patientDatabaseDao.insert(todoItem)
    }

    suspend fun updateTodo(todoItem: PatientItem) {
        patientDatabaseDao.update(todoItem)
    }

    suspend fun deleteTodo(todoItem: PatientItem) {
        patientDatabaseDao.delete(todoItem)
    }

    suspend fun deleteAllTodos() {
        patientDatabaseDao.deleteAllPatient()
    }
}