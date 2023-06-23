package com.example.google_task_project_dereza.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.google_task_project_dereza.data.database.DataAccessObject
import com.example.google_task_project_dereza.data.database.TaskDatabase
import com.example.google_task_project_dereza.data.models.TaskDataModel
import java.util.UUID

private const val DATABASE_NAME = "SQLiteDATABASE"

class TaskRepository private constructor(context: Context) {

    private val database: TaskDatabase = Room.databaseBuilder(
        context = context,
        TaskDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val dao: DataAccessObject = database.dataAccessObject()

    fun getTAsks(): LiveData<List<TaskDataModel>> = dao.getTasks()

    fun getFavoriteTasks(): LiveData<List<TaskDataModel>> = dao.getFavoriteTasks()

    fun getCompletedTasks(): LiveData<List<TaskDataModel>> = dao.getCompletedTasks()

    fun getSubtasks(parentID: UUID): LiveData<List<TaskDataModel>> = dao.getSubtasks(parentID)

    suspend fun addNewTask(task: TaskDataModel) = dao.addNewTask(task)

    suspend fun updateDataTask(task: TaskDataModel) = dao.updateDataTask(task)

    suspend fun deleteTask(task: TaskDataModel) = dao.deleteTask(task)
}