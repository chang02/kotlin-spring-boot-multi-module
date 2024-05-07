package com.pang.skeleton.apiserver.support.error

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiExceptionHandler {
    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(ApiServerException::class)
    fun handleApiServerException(e: ApiServerException): ResponseEntity<ApiServerErrorResponse> {
        logger.warn(e.message, e)
        return ResponseEntity(
            ApiServerErrorResponse(e.message),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleUnknownException(e: Exception): ResponseEntity<ApiServerErrorResponse> {
        logger.error(e.message, e)
        return ResponseEntity(
            ApiServerErrorResponse(e.message ?: "알 수 없는 에러 발생"),
            HttpStatus.BAD_REQUEST
        )
    }
}