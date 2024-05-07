package com.pang.skeleton.clients.randomuser

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "random-user", url = "https://randomuser.me/api")
interface RandomUserApi {
    @GetMapping("/")
    fun getRandomUser(): RandomUserResponse
}

data class RandomUserResponse(
    val results: List<Result>
) {
    data class Result(
        val name: Name,
    ) {
        data class Name(
            val title: String,
            val first: String,
            val last: String,
        )
    }
}