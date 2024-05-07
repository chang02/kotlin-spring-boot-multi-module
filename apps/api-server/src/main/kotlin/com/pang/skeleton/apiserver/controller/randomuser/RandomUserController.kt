package com.pang.skeleton.apiserver.controller.randomuser

import com.pang.skeleton.clients.randomuser.RandomUserClient
import com.pang.skeleton.common.util.response.ApiServerResponse
import com.pang.skeleton.common.util.response.toApiServerResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/random-users")
class RandomUserController(
    private val randomUserClient: RandomUserClient,
) {
    @GetMapping
    fun getRandomUser(): ApiServerResponse<String> {
        return randomUserClient.getRandomUser().toApiServerResponse()
    }
}