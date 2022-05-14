package com.axuca.todo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.axuca.todo.db.ToDoDatabase
import com.axuca.todo.model.ToDo

class ToDoRepo(private val database: ToDoDatabase) {

    private val _toDos: MutableLiveData<List<ToDo>> =
        Transformations.map(database.getToDoDao().getAllToDos()) {
            it
        } as MutableLiveData<List<ToDo>>

    val toDos: LiveData<List<ToDo>> get() = _toDos

    suspend fun addToDo(title: String) {
        database.getToDoDao().addToDo(ToDo(title = title))
    }

    suspend fun updateToDo(toDo: ToDo) {
        database.getToDoDao().updateToDo(toDo)
    }

    suspend fun deleteToDo(toDo: ToDo) {
        database.getToDoDao().deleteToDo(toDo)
    }

    suspend fun searchToDo(searchWord: String) {
        _toDos.value = database.getToDoDao().searchToDo(searchWord)
    }
}