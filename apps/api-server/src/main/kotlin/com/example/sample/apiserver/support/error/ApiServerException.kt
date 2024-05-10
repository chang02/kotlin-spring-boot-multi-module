package com.example.sample.apiserver.support.error

data class ApiServerException(
    override val message: String
) : Exception(message)