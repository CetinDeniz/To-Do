package com.axuca.todo.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.axuca.todo.db.ToDoDatabase
import com.axuca.todo.repository.ToDoRepo
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class AddVM(application: Application) : AndroidViewModel(application) {

    private val repo = ToDoRepo(ToDoDatabase.getDatabase(application))

    fun add(title: String) {
        if (title.isNotEmpty()) {
            viewModelScope.launch {
                repo.addToDo(title)
            }
        }
    }

}