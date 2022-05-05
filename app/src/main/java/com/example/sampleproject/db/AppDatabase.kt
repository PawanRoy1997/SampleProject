package com.example.sampleproject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Log::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getLogDao(): LogDao

    companion object{
        private lateinit var database: AppDatabase
        fun getDatabase(context: Context): AppDatabase {
            return if(this::database.isInitialized) database else Room.databaseBuilder(context,AppDatabase::class.java,"appDatabase.db").build()
        }
    }
}