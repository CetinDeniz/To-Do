package com.axuca.todo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.axuca.todo.model.ToDo

/**
 * Set exportSchema to false, so as not to keep schema version history backups.
 */
@Database(entities = [ToDo::class], version = 1, exportSchema = false)
abstract class ToDoDatabase : RoomDatabase() {

    abstract fun getToDoDao(): ToDoDao

    companion object {

        @Volatile
        private lateinit var DATABASE_INSTANCE: ToDoDatabase

        fun getDatabase(context: Context): ToDoDatabase {

            if (!::DATABASE_INSTANCE.isInitialized) { // First check
                synchronized(this) {
                    if (!::DATABASE_INSTANCE.isInitialized) { // Double check
                        val instance = Room
                            .databaseBuilder(
                                context.applicationContext,
                                ToDoDatabase::class.java,
                                "todo_database"
                            )
                            .fallbackToDestructiveMigration()
                            .build()

                        DATABASE_INSTANCE = instance
                    }
                }
            }
            return DATABASE_INSTANCE
        }


    }

}