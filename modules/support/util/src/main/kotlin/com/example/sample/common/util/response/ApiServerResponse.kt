package com.example.sample.common.util.response

data class ApiServerResponse<T>(
    val data: T?,
)

fun <T> T.toApiServerResponse() = ApiServerResponse<T>(this)