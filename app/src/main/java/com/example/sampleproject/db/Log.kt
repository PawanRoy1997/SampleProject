package com.example.sampleproject.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "logs")
data class Log(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val message: String,
    val timestamp: Long,
    val countValue: Int
)