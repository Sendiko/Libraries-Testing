package com.sendiko.librariestesting.dashboard

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
class DashboardScreenViewModel @Inject constructor(
    private val preferences: AppPreferences
): ViewModel() {

    private val _theme = preferences.getTheme()
    private val _themeBase = preferences.getThemeBase()
    private val _state = MutableStateFlow(DashboardScreenState())
    val state = combine(_theme, _themeBase, _state) { theme, base, state ->
        state.copy(isDarkTheme = theme, isThemeBasedSystem = base)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DashboardScreenState())

    fun onEvent(event: DashboardScreenEvent) {
        when(event) {
            is DashboardScreenEvent.OnDarkThemeToggle -> setTheme(event.darkTheme)
        }
    }

    private fun setTheme(darkTheme: Boolean) {
        viewModelScope.launch { preferences.setTheme(darkTheme) }
        _state.update {
            it.copy(isDarkTheme = darkTheme)
        }
    }

}