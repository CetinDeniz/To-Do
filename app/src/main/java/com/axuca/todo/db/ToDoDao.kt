package com.axuca.todo.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.axuca.todo.model.ToDo

/**
 * CREATE,READ,UPDATE,DELETE
 */
@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo")
    fun getAllToDos(): LiveData<List<ToDo>>

    @Query("SELECT * FROM todo WHERE title LIKE '%' || :searchWord || '%'")
    suspend fun searchToDo(searchWord: String): List<ToDo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToDo(vararg toDo: ToDo)

    @Update
    suspend fun updateToDo(toDo: ToDo)

    @Delete
    suspend fun deleteToDo(toDo: ToDo)
}