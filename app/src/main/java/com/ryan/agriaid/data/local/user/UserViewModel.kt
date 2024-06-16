package com.ryan.agriaid.data.local.user

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    val userFlow: StateFlow<User?> get() = userRepository.userFlow

    fun saveUser(user: User) {
        userRepository.saveUser(user)
    }

    fun clearUser() {
        userRepository.clearUser()
    }
}