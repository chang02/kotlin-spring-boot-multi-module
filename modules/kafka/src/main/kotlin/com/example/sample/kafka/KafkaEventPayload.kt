package com.example.sample.kafka

interface KafkaEventPayload {
    fun topic(): String
    fun partitionKey(): String
}