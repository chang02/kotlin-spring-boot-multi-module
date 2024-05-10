package com.example.sample.apiserver.controller.user

import com.example.sample.apiserver.controller.user.request.UserCreateRequest
import com.example.sample.apiserver.controller.user.request.UserUpdateRequest
import com.example.sample.apiserver.controller.user.response.UserResponse
import com.example.sample.apiserver.support.error.ApiServerException
import com.example.sample.domain.user.UserRepository
import com.example.sample.kafka.user.UserCreatedEventPayload
import com.example.sample.kafka.user.UserUpdatedEventPayload
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val eventPublisher: ApplicationEventPublisher,
) {
    @Cacheable(value = ["users"], key = "#id")
    @Transactional(readOnly = true)
    fun getUser(id: Long): UserResponse {
        val user = userRepository.findByIdOrNull(id) ?: throw ApiServerException("id-$id user 를 찾을 수 없습니다.")
        return UserResponse.fromEntity(user)
    }

    @Transactional
    fun createUser(request: UserCreateRequest): UserResponse {
        val user = userRepository.save(request.toEntity()).also {
            eventPublisher.publishEvent(UserCreatedEventPayload(it.id))
        }
        return UserResponse.fromEntity(user)
    }

    @Transactional
    fun updateUser(id: Long, request: UserUpdateRequest): UserResponse {
        val user = userRepository.findByIdOrNull(id) ?: throw ApiServerException("id-$id user 를 찾을 수 없습니다.")
        user.update(request.username, request.email)
        eventPublisher.publishEvent(UserUpdatedEventPayload(user.id))
        return UserResponse.fromEntity(userRepository.save(user))
    }

    @CacheEvict(value = ["users"], key = "#id")
    fun cacheEvict(id: Long) {
    }
}