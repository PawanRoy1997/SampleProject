package com.example.sampleproject.db

import javax.inject.Inject

class LogRepository @Inject constructor(private val dao: LogDao) {
    suspend fun addLog(message: String, timeStamp: Long, count: Int) {
        val log = Log(
            0, message, timeStamp, count
        )
        dao.addLog(log)
    }

    suspend fun getAllLogs(): List<Log> {
        return dao.getAllLogs().toList()
    }

    suspend fun deleteAll() {
        dao.deleteAllLogs()
    }
}