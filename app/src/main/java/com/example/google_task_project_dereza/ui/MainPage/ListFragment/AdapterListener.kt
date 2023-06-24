package com.example.google_task_project_dereza.ui.MainPage.ListFragment

import com.example.google_task_project_dereza.data.models.TaskDataModel

interface AdapterListener {
    fun onChanged(task: TaskDataModel)

    fun onDelete(task: TaskDataModel)
}