package com.example.google_task_project_dereza.ui.MainPage.MainPageFragment

import com.example.google_task_project_dereza.data.models.TaskDataModel

interface AddTaskListener {
    fun addTask(task: TaskDataModel)
}