package com.pang.skeleton.domain.user

data class User(
    var id: Long = 0,
    var username: String,
    var email: String
) {
    fun update(updateDto: UserUpdateDto) {
        updateDto.username?.let { this.username = it }
        updateDto.email?.let { this.email = it }
    }
}
