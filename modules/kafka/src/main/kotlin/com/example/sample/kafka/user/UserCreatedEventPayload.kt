package com.example.sample.kafka.user

import com.example.sample.kafka.KafkaEventPayload
import com.example.sample.kafka.KafkaTopics

class UserCreatedEventPayload(
    val id: Long,
) : KafkaEventPayload {
    override fun topic(): String {
        return KafkaTopics.USER_CREATED
    }

    override fun partitionKey(): String {
        return id.toString()
    }
}