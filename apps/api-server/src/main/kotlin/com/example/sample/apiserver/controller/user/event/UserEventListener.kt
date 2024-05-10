package com.example.sample.apiserver.controller.user.event

import com.example.sample.kafka.user.UserCreatedEventPayload
import com.example.sample.kafka.user.UserUpdatedEventPayload
import com.example.sample.kafka.KafkaProducer
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class UserEventListener(
    private val kafkaProducer: KafkaProducer,
) {
    @TransactionalEventListener
    fun userCreatedEventHandler(event: UserCreatedEventPayload) {
        kafkaProducer.produce(event)
    }

    @TransactionalEventListener
    fun userUpdatedEventHandler(event: UserUpdatedEventPayload) {
        kafkaProducer.produce(event)
    }
}