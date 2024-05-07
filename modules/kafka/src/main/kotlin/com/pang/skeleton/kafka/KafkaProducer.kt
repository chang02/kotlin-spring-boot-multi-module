package com.pang.skeleton.kafka

import com.pang.skeleton.common.util.response.toJsonString
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {
    fun produce(payload: KafkaEventPayload) {
        kafkaTemplate.send(payload.topic(), payload.partitionKey(), payload.toJsonString())
    }
}