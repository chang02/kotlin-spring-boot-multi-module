package com.example.sample.apiserver.controller.user.request

import com.example.sample.domain.user.User

data class UserCreateRequest(
    val username: String,
    val email: String
) {
    fun toEntity(): User {
        return User(
            username = username,
            email = email
        )
    }
}
