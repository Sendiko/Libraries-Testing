package com.sendiko.librariestesting.about

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sendiko.librariestesting.core.preference.AppPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutScreenViewModel @Inject constructor(
    private val preferences: AppPreferences
) : ViewModel() {

    private val _theme = preferences.getTheme()
    private val _themeBase = preferences.getThemeBase()
    private val _state = MutableStateFlow(AboutScreenState())
    val state = combine(_theme, _themeBase, _state) { theme, base, state ->
        state.copy(isDarkTheme = theme, isThemeBasedSystem = base)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AboutScreenState())

    fun onEvent(event: AboutScreenEvents) {
        when(event) {
            is AboutScreenEvents.OnDarkThemeToggle -> {
                viewModelScope.launch { preferences.setTheme(event.isDarkTheme) }
                _state.update {
                    it.copy(isDarkTheme = event.isDarkTheme)
                }
            }
            is AboutScreenEvents.OnThemeBaseToggle -> {
                viewModelScope.launch { preferences.setThemeBase(event.themeBasedSystem) }
                _state.update {
                    it.copy(isThemeBasedSystem = event.themeBasedSystem)
                }
            }
        }
    }

}