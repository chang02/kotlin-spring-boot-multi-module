package com.example.sample.kafka

import com.example.sample.common.util.response.toJsonString
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