package com.example.sampleproject.viewModels

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sampleproject.db.LogRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: LogRepository,
    private val dataStore: DataStore<Preferences>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass == MainViewModel::class.java) {
            MainViewModel(repository, dataStore) as T
        } else {
            modelClass.newInstance()
        }
    }
}