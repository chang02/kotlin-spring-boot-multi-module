package com.pang.skeleton.apiserver.support.error

data class ApiServerException(
    override val message: String
) : Exception(message)