package com.example.sample.apiserver.controller.user.response

import com.example.sample.domain.user.User

data class UserResponse(
    val id: Long,
    val username: String,
    val email: String
) {
    companion object {
        fun fromEntity(domain: User): UserResponse {
            return UserResponse(
                id = domain.id,
                username = domain.username,
                email = domain.email
            )
        }
    }
}
