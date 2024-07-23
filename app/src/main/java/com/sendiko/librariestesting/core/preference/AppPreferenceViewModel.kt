package com.sendiko.librariestesting.core.preference

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AppPreferenceViewModel @Inject constructor(
    private val preferences: AppPreferences
) : ViewModel() {

    private val _theme = preferences.getTheme()
    private val _themeBase = preferences.getThemeBase()
    private val _state = MutableStateFlow(ThemeState())
    val state = combine(_theme, _themeBase, _state) { theme, base, state ->
        state.copy(
            isDarkTheme = theme,
            isThemeBasedSystem = base
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ThemeState())

}