package com.pang.skeleton.domain.user

import com.pang.skeleton.domain.user.event.UserCreatedEventPayload
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class UserCreator(
    private val userRepository: UserRepository,
    private val eventPublisher: ApplicationEventPublisher,
) {
    fun createUser(user: User): User {
        val createdUser = userRepository.create(user)
        eventPublisher.publishEvent(UserCreatedEventPayload(createdUser.id))
        return createdUser
    }
}