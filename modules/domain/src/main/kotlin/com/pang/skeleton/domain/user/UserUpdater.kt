package com.pang.skeleton.domain.user

import com.pang.skeleton.domain.user.event.UserUpdatedEventPayload
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class UserUpdater(
    private val userRepository: UserRepository,
    private val eventPublisher: ApplicationEventPublisher,
) {
    fun updateUser(updateDto: UserUpdateDto): User {
        val user = userRepository.findByIdOrNull(updateDto.id)
            ?: throw IllegalStateException("can't update not exists user - ${updateDto.id}")
        user.update(updateDto)
        userRepository.update(user)
        eventPublisher.publishEvent(UserUpdatedEventPayload(user.id))
        return user
    }
}