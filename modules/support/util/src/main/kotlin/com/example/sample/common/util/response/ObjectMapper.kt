package com.example.sample.common.util.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

val objectMapper: ObjectMapper by lazy {
    val mapper = ObjectMapper().registerKotlinModule()
    mapper.registerModule(JavaTimeModule())
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
}

fun Any.toJsonString(): String = objectMapper.writeValueAsString(this)
inline fun <reified T : Any> String.toObject(): T = objectMapper.readValue(this)