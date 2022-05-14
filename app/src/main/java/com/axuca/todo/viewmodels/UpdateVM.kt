package com.axuca.todo.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.axuca.todo.db.ToDoDatabase
import com.axuca.todo.model.ToDo
import com.axuca.todo.repository.ToDoRepo
import kotlinx.coroutines.launch

class UpdateVM(application: Application) : AndroidViewModel(application) {
    private val repo = ToDoRepo(ToDoDatabase.getDatabase(application))

    init {
        Log.e("UpdateVM",": created")
    }

    fun update(id: Int, title: String) {
        Log.e("Update"," called")
        viewModelScope.launch {
            repo.updateToDo(
                ToDo(id, title)
            )
        }
    }

}