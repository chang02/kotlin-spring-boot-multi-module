package com.pang.skeleton.apiserver.controller.user.response

import com.pang.skeleton.domain.user.User

data class UserResponse(
    val id: Long,
    val username: String,
    val email: String
) {
    companion object {
        fun fromDomain(domain: User): UserResponse {
            return UserResponse(
                id = domain.id,
                username = domain.username,
                email = domain.email
            )
        }
    }
}
