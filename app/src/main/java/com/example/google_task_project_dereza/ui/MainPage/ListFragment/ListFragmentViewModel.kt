package com.example.google_task_project_dereza.ui.MainPage.ListFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.google_task_project_dereza.data.models.TaskDataModel
import com.example.google_task_project_dereza.data.repository.TaskRepository
import kotlinx.coroutines.launch

class ListFragmentViewModel:ViewModel() {
    private val repository = TaskRepository.get()

    val allTasks: LiveData<List<TaskDataModel>> = repository.getTasks()
    val favoriteTask: LiveData<List<TaskDataModel>> = repository.getFavoriteTasks()
    val completedTask: LiveData<List<TaskDataModel>> = repository.getCompletedTasks()

    fun onChangeTask(task: TaskDataModel){
        viewModelScope.launch {
            repository.updateDataTask(task)
        }
    }

    fun onDeleteTask(task: TaskDataModel){
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }
}