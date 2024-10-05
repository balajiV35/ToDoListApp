package com.example.todolistapp

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {
    private var nextId = 0
    val tasks = mutableStateListOf<Task>()

    fun addTask(title: String) {
        tasks.add(Task(nextId++, title))
    }

    fun updateTask(id: Int, newTitle: String) {
        tasks.find { it.id == id }?.title = newTitle
    }

    fun deleteTask(id: Int) {
        tasks.removeAll { it.id == id }
    }
}
