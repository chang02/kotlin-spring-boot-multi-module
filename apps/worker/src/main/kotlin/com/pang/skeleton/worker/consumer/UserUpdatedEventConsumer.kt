package com.pang.skeleton.worker.consumer

import com.pang.skeleton.common.util.response.toJsonString
import com.pang.skeleton.common.util.response.toObject
import com.pang.skeleton.domain.user.UserReader
import com.pang.skeleton.domain.user.event.UserCreatedEventPayload
import com.pang.skeleton.kafka.KafkaTopics
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class UserUpdatedEventConsumer(
    private val userReader: UserReader,
) {
    companion object {
        private val logger = KotlinLogging.logger {}
    }

    @KafkaListener(topics = [KafkaTopics.USER_UPDATED])
    fun consume(message: String) {
        logger.info { "received user updated event : $message" }
        val payload: UserCreatedEventPayload = message.toObject()
        val user = userReader.findById(payload.id)
        logger.info { "post process for updated user : ${user.toJsonString()}" }
    }
}