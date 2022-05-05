package com.example.sampleproject.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LogDao {
    @Query("SELECT * FROM logs ORDER BY timestamp DESC")
    suspend fun getAllLogs(): List<Log>

    @Insert
    suspend fun addLog(vararg log: Log)

    @Query("DELETE FROM logs")
    suspend fun deleteAllLogs()
}