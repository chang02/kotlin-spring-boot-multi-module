package com.pang.skeleton.common.util.response

data class CursorPageResponse<T>(
    val offset: Long,
    val content: List<T>,
    val totalElements: Long,
    val hasNext: Boolean,
    val contentSize: Int
)