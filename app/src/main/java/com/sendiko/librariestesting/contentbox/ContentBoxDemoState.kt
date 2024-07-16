package com.sendiko.librariestesting.contentbox

data class ContentBoxDemoState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val notificationMessage: String = "",
)
