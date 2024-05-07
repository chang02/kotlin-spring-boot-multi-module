package com.pang.skeleton.domain.user.event

import com.pang.skeleton.kafka.KafkaEventPayload
import com.pang.skeleton.kafka.KafkaTopics

class UserUpdatedEventPayload(
    val id: Long,
) : KafkaEventPayload {
    override fun topic(): String {
        return KafkaTopics.USER_UPDATED
    }

    override fun partitionKey(): String {
        return id.toString()
    }
}