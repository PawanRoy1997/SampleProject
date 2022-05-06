package com.example.sampleproject.viewModels

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.*
import com.example.sampleproject.db.Log
import com.example.sampleproject.db.LogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

private val LAST_COUNT_KEY = intPreferencesKey("last_count")

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: LogRepository,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {
    private val _logsLD: MutableLiveData<List<Log>> = MutableLiveData(ArrayList())
    val logsLD: LiveData<List<Log>> = _logsLD


    private val lastCountFlow: Flow<Int> = dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
        val lastCount = preferences[LAST_COUNT_KEY] ?: 0
        lastCount
    }
//    private val _lastCountLD: MutableLiveData<Int> = MutableLiveData(0)
    val lastCountLD: LiveData<Int> = lastCountFlow.asLiveData()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            _logsLD.postValue(repository.getAllLogs())
        }
    }

    fun getAllLogs() {
        viewModelScope.launch(Dispatchers.Default) { _logsLD.postValue(repository.getAllLogs()) }
    }

    fun addNewLog(message: String, timeStamp: Long, count: Int) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.addLog(message, timeStamp, count)
            updateLastCount(count)
        }
    }

    fun deleteAllLogs() {
        viewModelScope.launch(Dispatchers.Default) {
            repository.deleteAll()
            updateLastCount(0)
        }
    }

    private suspend fun updateLastCount(count: Int) {
        dataStore.edit {preferences ->
            preferences[LAST_COUNT_KEY] = count
        }
    }
}