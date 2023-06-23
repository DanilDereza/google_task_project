package com.example.google_task_project_dereza

import android.app.Application
import com.example.google_task_project_dereza.data.repository.TaskRepository

class TaskApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        TaskRepository.init(this)
    }
}