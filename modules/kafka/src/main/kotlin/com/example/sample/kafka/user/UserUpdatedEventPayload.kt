package com.example.sample.kafka.user

import com.example.sample.kafka.KafkaEventPayload
import com.example.sample.kafka.KafkaTopics

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