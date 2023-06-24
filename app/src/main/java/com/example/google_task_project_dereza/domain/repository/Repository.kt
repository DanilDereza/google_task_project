package com.example.google_task_project_dereza.domain.repository

import androidx.lifecycle.LiveData
import com.example.google_task_project_dereza.data.database.DataAccessObject
import com.example.google_task_project_dereza.data.models.TaskDataModel
import java.util.UUID

interface Repository {
    fun getTasks(): LiveData<List<TaskDataModel>>

    fun getFavoriteTasks(): LiveData<List<TaskDataModel>>

    fun getCompletedTasks(): LiveData<List<TaskDataModel>>

    fun getSubtasks(parentID: UUID): LiveData<List<TaskDataModel>>

    suspend fun addNewTask(task: TaskDataModel)

    suspend fun updateDataTask(task: TaskDataModel)

    suspend fun deleteTask(task: TaskDataModel)
}