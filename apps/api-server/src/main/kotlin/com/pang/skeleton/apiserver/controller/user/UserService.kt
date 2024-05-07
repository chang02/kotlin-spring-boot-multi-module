package com.pang.skeleton.apiserver.controller.user

import com.pang.skeleton.apiserver.controller.user.request.UserCreateRequest
import com.pang.skeleton.apiserver.controller.user.request.UserUpdateRequest
import com.pang.skeleton.apiserver.support.error.ApiServerException
import com.pang.skeleton.domain.user.*
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userReader: UserReader,
    private val userCreator: UserCreator,
    private val userUpdater: UserUpdater,
) {
    @Cacheable(value = ["users"], key = "#id")
    @Transactional(readOnly = true)
    fun getUser(id: Long): User {
        return try {
            userReader.findById(id)
        } catch (e: NotFoundUserException) {
            throw ApiServerException(e.message)
        }
    }

    @Transactional
    fun createUser(request: UserCreateRequest): User {
        return userCreator.createUser(request.toDomain())
    }

    @Transactional
    fun updateUser(id: Long, request: UserUpdateRequest): User {
        return userUpdater.updateUser(UserUpdateDto(id, request.username, request.email))
    }

    @CacheEvict(value = ["users"], key = "#id")
    fun cacheEvict(id: Long) {
    }
}