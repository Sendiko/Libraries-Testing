package com.sendiko.librariestesting.selector

import com.sendiko.selector_component.SelectorData

sealed class SelectorDemoScreenEvent {
    data class OnSelect(val selectorData: SelectorData): SelectorDemoScreenEvent()
}