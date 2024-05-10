package com.example.sample.worker.support.error

data class WorkerException(
    override val message: String
) : Exception(message)