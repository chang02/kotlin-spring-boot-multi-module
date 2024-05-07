package com.pang.skeleton.domain.user

data class UserUpdateDto(
    val id: Long,
    val username: String?,
    val email: String?,
)