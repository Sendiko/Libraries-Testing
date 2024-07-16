package com.sendiko.librariestesting.selector

import com.sendiko.selector_component.SelectorData

data class SelectorDemoScreenState(
    val items: List<SelectorData> = emptyList(),
    val currentlySelected: SelectorData = items.first(),
)
