package com.example.sample.clients.randomuser

import org.springframework.stereotype.Component

@Component
class RandomUserClient(
    private val randomUserApi: RandomUserApi,
) {
    fun getRandomUser(): String {
        val ru = randomUserApi.getRandomUser().results.first()
        return "${ru.name.first} ${ru.name.last}"
    }
}