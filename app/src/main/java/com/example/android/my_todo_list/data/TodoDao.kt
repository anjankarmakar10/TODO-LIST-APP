package com.example.android.my_todo_list.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)

    @Query("select * from todo where id= :id")
    suspend fun getTodoId(id: Int): Todo?

    @Query("select * from todo")
    fun getTodos(): Flow<List<Todo>>
}