package com.ryan.agriaid.data.local.user

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    private val _userFlow: MutableStateFlow<User?> = MutableStateFlow(null)
    val userFlow: StateFlow<User?> get() = _userFlow.asStateFlow()

    init {
        loadUser()
    }

    fun saveUser(user: User) {
        with(sharedPreferences.edit()) {
            putInt("id", user.id)
            putString("username", user.username)
            putString("role", user.role)
            putString("imageUrl", user.imageUrl)
            apply()
        }
        _userFlow.value = user
    }

    fun clearUser() {
        with(sharedPreferences.edit()) {
            clear()
            apply()
        }
        _userFlow.value = null
    }

    private fun loadUser() {
        val id = sharedPreferences.getInt("id", -1)
        if (id != -1) {
            val username = sharedPreferences.getString("username", "") ?: ""
            val role = sharedPreferences.getString("role", "") ?: ""
            val imageUrl = sharedPreferences.getString("imageUrl", "") ?: ""
            _userFlow.value = User(id, username, role, imageUrl)
        } else {
            _userFlow.value = null
        }
    }
}