package com.pang.skeleton.apiserver.controller.user.request

import com.pang.skeleton.domain.user.User

data class UserCreateRequest(
    val username: String,
    val email: String
) {
    fun toDomain(): User {
        return User(
            username = username,
            email = email
        )
    }
}
