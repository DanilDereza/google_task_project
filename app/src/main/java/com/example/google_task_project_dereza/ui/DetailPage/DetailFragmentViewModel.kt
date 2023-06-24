package com.example.google_task_project_dereza.ui.DetailPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.google_task_project_dereza.data.models.TaskDataModel
import com.example.google_task_project_dereza.data.repository.TaskRepository
import kotlinx.coroutines.launch
import java.util.UUID

class DetailFragmentViewModel:ViewModel() {
    private val repository = TaskRepository.get()

    private val taskId = MutableLiveData<UUID>()

    val task: LiveData<TaskDataModel> = Transformations.switchMap(taskId){
        repository.getTask(it)
    }

    val subtasks: LiveData<List<TaskDataModel>> = Transformations.switchMap(taskId){
        repository.getSubtasks(it)
    }

    fun setTaskId(id:UUID) {taskId.value = id}

    fun addSubtask(subtask:TaskDataModel){
        viewModelScope.launch {
            repository.addNewTask(subtask)
        }
    }

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