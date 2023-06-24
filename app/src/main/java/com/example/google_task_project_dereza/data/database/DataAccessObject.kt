package com.example.google_task_project_dereza.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.google_task_project_dereza.data.models.TaskDataModel
import java.util.UUID

@Dao
interface DataAccessObject {

    @Query("SELECT * FROM TaskDataModel WHERE isDone = 0 AND subtask IS NULL")
    fun getTasks(): LiveData<List<TaskDataModel>>

    @Query("SELECT * FROM TaskDataModel WHERE isDone = 0 AND subtask IS NULL AND isFavorite = 1")
    fun getFavoriteTasks(): LiveData<List<TaskDataModel>>

    @Query("SELECT * FROM TaskDataModel WHERE isDone = 1 AND subtask IS NULL")
    fun getCompletedTasks(): LiveData<List<TaskDataModel>>

    @Query("SELECT * FROM TaskDataModel WHERE subtask=(:parentID)")
    fun getSubtasks(parentID: UUID): LiveData<List<TaskDataModel>>

    @Query("SELECT * FROM TaskDataModel WHERE id=(:taskId)")
    fun getTask(taskId: UUID): LiveData<TaskDataModel>

    @Insert
    suspend fun addNewTask(task:TaskDataModel)

    @Update
    suspend fun updateDataTask(task: TaskDataModel)

    @Delete
    suspend fun deleteTask(task: TaskDataModel)
}