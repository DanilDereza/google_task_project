package com.example.google_task_project_dereza.ui.DetailPage

import com.example.google_task_project_dereza.data.models.TaskDataModel
import com.example.google_task_project_dereza.ui.MainPage.MainPageFragment.AddTaskListener

interface AddSubtaskListener{
    fun addSubtask(subtask:TaskDataModel)
}