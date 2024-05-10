package com.example.sample.worker.consumer

import com.example.sample.common.util.response.toJsonString
import com.example.sample.common.util.response.toObject
import com.example.sample.domain.user.UserRepository
import com.example.sample.kafka.KafkaTopics.USER_CREATED
import com.example.sample.kafka.user.UserCreatedEventPayload
import com.example.sample.worker.support.error.WorkerException
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.data.repository.findByIdOrNull
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class UserCreatedEventConsumer(
    private val userRepository: UserRepository,
) {
    companion object {
        private val logger = KotlinLogging.logger {}
    }

    @KafkaListener(topics = [USER_CREATED])
    fun consume(message: String) {
        logger.info { "received user created event : $message" }
        val payload: UserCreatedEventPayload = message.toObject()
        val user = userRepository.findByIdOrNull(payload.id)
            ?: throw WorkerException("id-${payload.id} user 를 찾을 수 없습니다.")
        logger.info { "post process for created user : ${user.toJsonString()}" }
    }
}