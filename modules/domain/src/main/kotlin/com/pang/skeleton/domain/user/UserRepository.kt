package com.pang.skeleton.domain.user

interface UserRepository {
    fun findByIdOrNull(id: Long): User?

    fun create(user: User): User

    fun update(user: User): User
}