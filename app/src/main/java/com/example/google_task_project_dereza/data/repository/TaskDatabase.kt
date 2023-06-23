package com.example.google_task_project_dereza.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.google_task_project_dereza.data.database.DataAccessObject
import com.example.google_task_project_dereza.data.models.TaskDataModel

@Database(entities = [TaskDataModel::class], version = 1, exportSchema = false)
abstract class Database: RoomDatabase() {
    abstract fun dataAccessObject(): DataAccessObject
}