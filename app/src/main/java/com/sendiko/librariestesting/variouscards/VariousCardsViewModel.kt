package com.sendiko.librariestesting.variouscards

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class VariousCardsViewModel: ViewModel() {

    private val _state = MutableStateFlow(VariousCardsState())
    val state = _state.asStateFlow()

    fun onEvent(event: VariousCardsEvent) {
        when (event) {
            is VariousCardsEvent.OnSelectCard -> {
                if (state.value.isSelected.contains(event.card)) {
                    _state.update { it.copy(isSelected = it.isSelected.minus(event.card)) }
                } else {
                    _state.update { it.copy(isSelected = it.isSelected.plus(event.card)) }
                }
            }
        }
    }

}