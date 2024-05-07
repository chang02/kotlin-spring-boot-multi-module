package com.pang.skeleton.redis

object CacheKeys {
    private const val USERS = "users"
    val keysTtlMap = mapOf(
        USERS to 30L
    )
}