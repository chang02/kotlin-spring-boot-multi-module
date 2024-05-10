package com.example.sample.apiserver.controller.user

import com.example.sample.apiserver.controller.user.request.UserCreateRequest
import com.example.sample.apiserver.controller.user.request.UserUpdateRequest
import com.example.sample.apiserver.controller.user.response.UserResponse
import com.example.sample.common.util.response.ApiServerResponse
import com.example.sample.common.util.response.toApiServerResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService,
) {
    @GetMapping("/{id}")
    fun findUserById(
        @PathVariable("id") id: Long
    ): ApiServerResponse<UserResponse> {
        return userService.getUser(id).toApiServerResponse()
    }

    @PostMapping
    fun createUser(
        @RequestBody request: UserCreateRequest
    ): ApiServerResponse<Long> {
        return userService.createUser(request).id.toApiServerResponse()
    }

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @RequestBody request: UserUpdateRequest
    ): ApiServerResponse<Long> {
        userService.updateUser(id, request)
        userService.cacheEvict(id)
        return id.toApiServerResponse()
    }
}
