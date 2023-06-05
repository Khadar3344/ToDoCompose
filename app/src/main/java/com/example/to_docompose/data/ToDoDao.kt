package com.example.to_docompose.data

import androidx.room.*
import com.example.to_docompose.data.models.ToDoTask
import kotlinx.coroutines.flow.Flow

// Data Access Object - where you define your database interactions.
@Dao
interface ToDoDao {

    // The database operations can take a long time to execute, so they should run on a separate
    // thread. Make the function a suspend function, so that this function can be called from a coroutine.
    // The argument OnConflict tells the Room what to do in case of a conflict.
    // The OnConflictStrategy.IGNORE strategy ignores a new item if it's primary key is already in the database.

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(toDoTask: ToDoTask)

    @Update
    suspend fun updateTask(toDoTask: ToDoTask)

    @Delete
    suspend fun deleteTask(toDoTask: ToDoTask)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAllTasks()


    // Using Flow or LiveData as return type will ensure you get notified whenever the data in the database changes.
    // It is recommended to use Flow in the persistence layer.
    // The Room keeps this Flow updated for you, which means you only need to explicitly get the data once.
    // Because of the Flow return type, Room also runs the query on the background thread.
    // You don't need to explicitly make it a suspend function and call inside a coroutine scope.

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllTasks(): Flow<List<ToDoTask>>

    @Query("SELECT * FROM todo_table WHERE id=:taskId")
    fun getSelectedTask(taskId: Int): Flow<ToDoTask>

    @Query("SELECT * FROM todo_table WHERE title LIKE :searchQuery OR description LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<ToDoTask>>

    @Query(
        """
                SELECT * FROM todo_table ORDER BY
            CASE
                WHEN priority LIKE 'L%' THEN 1
                WHEN priority LIKE 'M%' THEN 2
                WHEN priority LIKE 'H%' THEN 3
            END
        """
    )
    fun sortByLowPriority(): Flow<List<ToDoTask>>

    @Query(
        """
                SELECT * FROM todo_table ORDER BY
            CASE
                WHEN priority LIKE 'H%' THEN 1
                WHEN priority LIKE 'M%' THEN 2
                WHEN priority LIKE 'L%' THEN 3 
            END
        """
    )
    fun sortByHighPriority(): Flow<List<ToDoTask>>
}