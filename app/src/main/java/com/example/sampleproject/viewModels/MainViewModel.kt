package com.example.sampleproject.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleproject.db.Log
import com.example.sampleproject.db.LogRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: LogRepository) : ViewModel() {
    private val _logsLD: MutableLiveData<List<Log>> = MutableLiveData(ArrayList())
    val logsLD: LiveData<List<Log>> = _logsLD
    private val _lastCountLD: MutableLiveData<Int> = MutableLiveData(0)
    val lastCountLD: LiveData<Int> = _lastCountLD

    init {
        viewModelScope.launch(Dispatchers.Default) {
            _logsLD.postValue(repository.getAllLogs())
        }
    }

    fun getLastCount() {
        viewModelScope.launch(Dispatchers.Default) {
            val logs = async { repository.getAllLogs() }
            val receivedLogs = logs.await()
            val lastCount = if (receivedLogs.isEmpty()) 0 else receivedLogs.first().countValue
            _lastCountLD.postValue(lastCount)
        }
    }

    fun getAllLogs() {
        viewModelScope.launch(Dispatchers.Default) { _logsLD.postValue(repository.getAllLogs()) }
    }

    fun addNewLog(message: String, timeStamp: Long, count: Int) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.addLog(message, timeStamp, count)
        }
        _lastCountLD.postValue(count)
    }

    fun deleteAllLogs() {
        viewModelScope.launch(Dispatchers.Default) {
            repository.deleteAll()
            _lastCountLD.postValue(0)
        }
    }
}