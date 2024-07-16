package com.sendiko.librariestesting.selector

import androidx.lifecycle.ViewModel
import com.sendiko.selector_component.SelectorData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SelectorDemoScreenViewModel: ViewModel() {

    private val items = listOf(
        SelectorData(
            index = 0,
            label = "First"
        ),
        SelectorData(
            index = 1,
            label = "Second"
        ),
        SelectorData(
            index = 0,
            label = "Third"
        ),
    )
    private val _state = MutableStateFlow(SelectorDemoScreenState(items = items))
    val state = _state.asStateFlow()

    fun onEvent(event: SelectorDemoScreenEvent) {
        when (event) {
            is SelectorDemoScreenEvent.OnSelect -> _state.update {
                it.copy(currentlySelected = event.selectorData)
            }
        }
    }

}