package com.example.google_task_project_dereza.ui.MainPage.ListFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.google_task_project_dereza.data.models.TaskDataModel
import com.example.google_task_project_dereza.data.repository.TaskRepository

class ListFragmentViewModel:ViewModel() {
    private val repository = TaskRepository.get()

    val allTasks: LiveData<List<TaskDataModel>> = repository.getTasks()
    val favoriteTask: LiveData<List<TaskDataModel>> = repository.getFavoriteTasks()
    val completedTask: LiveData<List<TaskDataModel>> = repository.getCompletedTasks()
}