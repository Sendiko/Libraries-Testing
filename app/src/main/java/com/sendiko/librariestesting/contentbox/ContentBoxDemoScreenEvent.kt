package com.sendiko.librariestesting.contentbox

sealed class ContentBoxDemoScreenEvent {
    data class OnSetLoading(val isLoading: Boolean): ContentBoxDemoScreenEvent()
    data class OnSetError(val isError: Boolean): ContentBoxDemoScreenEvent()
    data object OnSetMessage: ContentBoxDemoScreenEvent()
    data object ClearState: ContentBoxDemoScreenEvent()
}