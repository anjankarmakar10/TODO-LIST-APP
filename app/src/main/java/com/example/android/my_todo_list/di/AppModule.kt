package com.example.android.my_todo_list.di

import android.app.Application
import androidx.room.Room
import com.example.android.my_todo_list.data.TodoDatabase
import com.example.android.my_todo_list.data.TodoRepository
import com.example.android.my_todo_list.data.TodoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(application: Application): TodoDatabase {
        return Room.databaseBuilder(
            application,
            TodoDatabase::class.java,
            "todo_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: TodoDatabase): TodoRepositoryImpl {
        return TodoRepositoryImpl(db.dao())
    }

}