package com.pang.skeleton.jpa.user

import com.pang.skeleton.domain.user.User
import com.pang.skeleton.domain.user.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
internal class UserRepositoryJpaImpl(
    private val userJpaRepository: UserJpaRepository
) : UserRepository {
    override fun findByIdOrNull(id: Long): User? {
        return userJpaRepository.findByIdOrNull(id)?.toDomain()
    }

    override fun create(user: User): User {
        return userJpaRepository.save(UserEntity.fromDomain(user)).toDomain()
    }

    override fun update(user: User): User {
        val userEntity = userJpaRepository.findByIdOrNull(user.id)
            ?: throw IllegalStateException("can't update not exists user")
        userEntity.copyFromDomain(user)
        return userJpaRepository.save(userEntity).toDomain()
    }
}