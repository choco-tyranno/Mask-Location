package com.choco_tyranno.studymvvmkotlina1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(applicationContext : Application) : AndroidViewModel(applicationContext) {
    private val db = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, "todo-db"
    ).build()

    var todos : LiveData<List<Todo>>

    var newTodo : String? = null

    init {
        todos = getAll()
    }

    fun getAll() : LiveData<List<Todo>> {
        return db.todoDao().getAll()
    }

    fun insert(todo : String) {
        viewModelScope.launch(Dispatchers.IO){
            db.todoDao().insert(Todo(todo))
        }
    }
}