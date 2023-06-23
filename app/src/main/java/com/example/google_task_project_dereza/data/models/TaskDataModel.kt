package com.example.google_task_project_dereza.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class TaskDataModel(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    var taskName: String,
    var taskDescription: String,
    var isFavorite: Boolean,
    var isDone: Boolean,
    var subtask: UUID? = null
)
