package com.example.sampleproject.di

import androidx.lifecycle.ViewModelProvider
import com.example.sampleproject.db.LogDao
import com.example.sampleproject.db.LogRepository
import com.example.sampleproject.viewModels.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@InstallIn(ActivityRetainedComponent::class)
@Module
class ViewModelModule {
    @Provides
    fun provideLogRepository(logDao: LogDao): LogRepository {
        return LogRepository(logDao)
    }

    @Provides
    @ActivityRetainedScoped
    fun provideViewModelFactory(logRepository: LogRepository): ViewModelProvider.Factory {
        return ViewModelFactory(logRepository)
    }
}