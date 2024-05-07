package com.pang.skeleton.kafka

interface KafkaEventPayload {
    fun topic(): String
    fun partitionKey(): String
}