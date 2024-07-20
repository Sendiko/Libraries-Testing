package com.sendiko.librariestesting.varioustextfields

import androidx.lifecycle.ViewModel
import com.sendiko.librariestesting.varioustextfields.VariousTextFieldScreenEvent.OnMultipleTextFieldTextChange
import com.sendiko.librariestesting.varioustextfields.VariousTextFieldScreenEvent.OnPasswordToggle
import com.sendiko.librariestesting.varioustextfields.VariousTextFieldScreenEvent.OnRoundedPasswordTextFieldTextChange
import com.sendiko.librariestesting.varioustextfields.VariousTextFieldScreenEvent.OnRoundedTextFieldTextChange
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class VariousTextFieldScreenViewModel: ViewModel() {

    private val _state = MutableStateFlow(VariousTextFieldScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: VariousTextFieldScreenEvent) {
        when(event){
            is OnMultipleTextFieldTextChange -> _state.update {
                it.copy(multipleTextFieldText = event.text)
            }
            is OnPasswordToggle -> _state.update {
                it.copy(passwordVisible = event.isVisible)
            }
            is OnRoundedPasswordTextFieldTextChange -> _state.update {
                it.copy(roundedTextFieldText = event.text)
            }
            is OnRoundedTextFieldTextChange -> _state.update {
                it.copy(roundedTextFieldText = event.text)
            }
        }
    }

}