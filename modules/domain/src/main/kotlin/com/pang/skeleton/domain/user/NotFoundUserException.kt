package com.pang.skeleton.domain.user

data class NotFoundUserException(
    override val message: String,
) : Exception(message)