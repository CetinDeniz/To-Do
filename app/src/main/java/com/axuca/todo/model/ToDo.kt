package com.axuca.todo.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "todo")
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, /** Room will handle */

    @ColumnInfo(name = "title")
    val title: String
) : Parcelable