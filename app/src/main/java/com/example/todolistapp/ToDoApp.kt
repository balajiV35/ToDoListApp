package com.example.todolistapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ToDoApp(taskViewModel: TaskViewModel = viewModel()) {
    var newTaskTitle by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Row {
            BasicTextField(
                value = newTaskTitle,
                onValueChange = { newTaskTitle = it },
                modifier = Modifier.weight(1f).padding(8.dp)
            )
            Button(onClick = {
                taskViewModel.addTask(newTaskTitle)
                newTaskTitle = ""
            }) {
                Text("Add Task")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        TaskList(taskViewModel)
    }
}

@Composable
fun TaskList(taskViewModel: TaskViewModel) {
    Column {
        taskViewModel.tasks.forEach { task ->
            TaskItem(task, taskViewModel)
        }
    }
}

@Composable
fun TaskItem(task: Task, taskViewModel: TaskViewModel) {
    var isEditing by remember { mutableStateOf(false) }
    var editedTitle by remember { mutableStateOf(task.title) }

    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        if (isEditing) {
            BasicTextField(
                value = editedTitle,
                onValueChange = { editedTitle = it },
                modifier = Modifier.weight(1f).padding(8.dp)
            )
            Button(onClick = {
                taskViewModel.updateTask(task.id, editedTitle)
                isEditing = false
            }) {
                Text("Save")
            }
        } else {
            Text(task.title, modifier = Modifier.weight(1f).padding(8.dp))
            Button(onClick = { isEditing = true }) {
                Text("Edit")
            }
        }
        Button(onClick = { taskViewModel.deleteTask(task.id) }) {
            Text("Delete")
        }
    }
}
