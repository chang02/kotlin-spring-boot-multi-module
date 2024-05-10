package com.example.sample.domain.user

data class NotFoundUserException(
    override val message: String,
) : Exception(message)