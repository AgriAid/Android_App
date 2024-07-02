package com.ryan.agriaid.data.local.user

import kotlinx.coroutines.flow.StateFlow

class UserRepository(private val userPreferences: UserPreferences) {

    val userFlow: StateFlow<User?> get() = userPreferences.userFlow

    fun saveUser(user: User) {
        userPreferences.saveUser(user)
    }

    fun clearUser() {
        userPreferences.clearUser()
    }
}