package com.example.google_task_project_dereza.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.google_task_project_dereza.data.database.DataAccessObject
import com.example.google_task_project_dereza.data.database.TaskDatabase
import com.example.google_task_project_dereza.data.models.TaskDataModel
import com.example.google_task_project_dereza.domain.repository.Repository
import java.lang.IllegalStateException
import java.util.UUID

private const val DATABASE_NAME = "SQLiteDATABASE"

class TaskRepository private constructor(context: Context):Repository {

    private val database: TaskDatabase = Room.databaseBuilder(
        context = context,
        TaskDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val dao: DataAccessObject = database.dataAccessObject()

    override fun getTasks(): LiveData<List<TaskDataModel>> = dao.getTasks()

    override fun getFavoriteTasks(): LiveData<List<TaskDataModel>> = dao.getFavoriteTasks()

    override fun getCompletedTasks(): LiveData<List<TaskDataModel>> = dao.getCompletedTasks()

    override fun getSubtasks(parentID: UUID): LiveData<List<TaskDataModel>> = dao.getSubtasks(parentID)

    override fun getTask(taskId: UUID): LiveData<TaskDataModel> = dao.getTask(taskId)

    override suspend fun addNewTask(task: TaskDataModel) = dao.addNewTask(task)

    override suspend fun updateDataTask(task: TaskDataModel) = dao.updateDataTask(task)

    override suspend fun deleteTask(task: TaskDataModel) = dao.deleteTask(task)

    companion object{
        private var repository: TaskRepository? = null

        fun get():TaskRepository{
            return repository ?: throw IllegalStateException("The repository is not initialized")
        }

        fun init(context: Context){
            if (repository == null)
                repository = TaskRepository(context)
        }
    }
}