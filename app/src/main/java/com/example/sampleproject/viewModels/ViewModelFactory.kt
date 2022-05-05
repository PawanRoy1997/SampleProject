package com.example.sampleproject.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sampleproject.db.LogRepository

class ViewModelFactory(private val repository: LogRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass == MainViewModel::class.java){
            MainViewModel(repository) as T
        }else{
            modelClass.newInstance()
        }
    }
}