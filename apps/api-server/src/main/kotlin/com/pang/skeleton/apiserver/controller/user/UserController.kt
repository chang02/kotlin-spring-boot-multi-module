package com.pang.skeleton.apiserver.controller.user

import com.pang.skeleton.apiserver.controller.user.request.UserCreateRequest
import com.pang.skeleton.apiserver.controller.user.request.UserUpdateRequest
import com.pang.skeleton.apiserver.controller.user.response.UserResponse
import com.pang.skeleton.common.util.response.ApiServerResponse
import com.pang.skeleton.common.util.response.toApiServerResponse
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
        val findUser = userService.getUser(id)
        return UserResponse.fromDomain(findUser).toApiServerResponse()
    }

    @PostMapping
    fun createUser(
        @RequestBody request: UserCreateRequest
    ): ApiServerResponse<Long> {
        val created = userService.createUser(request)
        return created.id.toApiServerResponse()
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
