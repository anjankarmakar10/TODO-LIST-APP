package com.example.android.my_todo_list.util

import com.example.android.my_todo_list.data.Todo

sealed class UiEvent() {
    object PopBackStack : UiEvent()
    data class Navigate(val route: String) : UiEvent()
    data class ShowSnackBar(val message: String, val action: String? = null) : UiEvent()
}