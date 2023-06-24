package com.example.google_task_project_dereza.ui.MainPage.MainPageFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.google_task_project_dereza.data.models.TaskDataModel
import com.example.google_task_project_dereza.data.repository.TaskRepository
import kotlinx.coroutines.launch

class MainPageFragmentViewModel:ViewModel() {
    var repository = TaskRepository.get()

    fun addNewTask(task:TaskDataModel){
        viewModelScope.launch {
            repository.addNewTask(task)
        }
    }
}