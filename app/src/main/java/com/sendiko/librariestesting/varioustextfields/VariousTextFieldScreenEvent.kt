package com.sendiko.librariestesting.varioustextfields

sealed class VariousTextFieldScreenEvent {
    data class OnRoundedTextFieldTextChange(val text: String): VariousTextFieldScreenEvent()
    data class OnRoundedPasswordTextFieldTextChange(val text: String): VariousTextFieldScreenEvent()
    data class OnMultipleTextFieldTextChange(val text: String): VariousTextFieldScreenEvent()
    data class OnPasswordToggle(val isVisible: Boolean): VariousTextFieldScreenEvent()
}
