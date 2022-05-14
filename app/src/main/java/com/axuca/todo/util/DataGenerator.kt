package com.axuca.todo.util

import com.axuca.todo.model.ToDo

fun generateData(): List<ToDo>{
    return listOf(
        ToDo(title = "Research"),
        ToDo(title = "Learn"),
        ToDo(title = "Apply"),
    )
}