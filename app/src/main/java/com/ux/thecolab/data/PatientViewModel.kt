package com.ux.thecolab.data

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PatientViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<PatientItem>>
    private val repository: PatientRepository

    init {
        val todoDao = ColabDatabase.getInstance(application).todoDao()
        repository = PatientRepository(todoDao)
        readAllData = repository.readAllData
    }

    fun addTodo(todoItem: PatientItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTodo(todoItem)
        }
    }
}

class PatientViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(PatientViewModel::class.java)) {
            return PatientViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}