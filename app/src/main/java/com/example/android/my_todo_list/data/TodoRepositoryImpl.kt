package com.example.android.my_todo_list.data

import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(private val dao: TodoDao) : TodoRepository {
    override suspend fun insert(todo: Todo) {
        dao.insert(todo)
    }

    override suspend fun delete(todo: Todo) {
        dao.delete(todo)
    }

    override suspend fun getTodoById(id: Int): Todo? {
        return dao.getTodoId(id)
    }

    override fun getTodos(): Flow<List<Todo>> {
        return dao.getTodos()
    }

}