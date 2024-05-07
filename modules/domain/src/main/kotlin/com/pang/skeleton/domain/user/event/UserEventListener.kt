package com.pang.skeleton.domain.user.event

import com.pang.skeleton.kafka.KafkaProducer
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