package com.pang.skeleton.domain.user

import org.springframework.stereotype.Component

@Component
class UserReader(
    private val userRepository: UserRepository,
) {
    fun findById(id: Long): User {
        return userRepository.findByIdOrNull(id)
            ?: throw NotFoundUserException("id-$id user 를 찾을 수 없습니다.")
    }
}