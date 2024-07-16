package com.sendiko.librariestesting.contentbox

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ContentBoxDemoScreenViewModel: ViewModel() {

    private val _state = MutableStateFlow(ContentBoxDemoState())
    val state = _state.asStateFlow()

    fun onEvent(event: ContentBoxDemoScreenEvent){
        when (event) {
            is ContentBoxDemoScreenEvent.OnSetError -> _state.update {
                it.copy(isLoading = false, isError = event.isError, notificationMessage = "Error Example.")
            }
            is ContentBoxDemoScreenEvent.OnSetLoading -> _state.update {
                it.copy(isLoading = event.isLoading, isError = false, notificationMessage = "")
            }
            is ContentBoxDemoScreenEvent.OnSetMessage -> _state.update {
                it.copy(isLoading = false, isError = false, notificationMessage = "Notification Example.")
            }
            ContentBoxDemoScreenEvent.ClearState -> _state.update {
                ContentBoxDemoState()
            }
        }
    }
}