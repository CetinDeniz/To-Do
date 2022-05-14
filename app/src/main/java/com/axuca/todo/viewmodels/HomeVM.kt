package com.axuca.todo.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.axuca.todo.db.ToDoDatabase
import com.axuca.todo.model.ToDo
import com.axuca.todo.repository.ToDoRepo
import kotlinx.coroutines.launch

class HomeVM(application: Application) : AndroidViewModel(application) {
    private val repo = ToDoRepo(ToDoDatabase.getDatabase(application))

    val toDos get() = repo.toDos

    fun searchToDos(searchWord: String) {
        viewModelScope.launch {
            repo.searchToDo(searchWord)
        }
    }

    fun deleteToDo(toDo: ToDo){
        viewModelScope.launch {
            repo.deleteToDo(toDo)
        }
    }
}