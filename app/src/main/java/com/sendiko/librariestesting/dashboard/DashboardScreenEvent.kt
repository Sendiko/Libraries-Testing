package com.sendiko.librariestesting.dashboard

sealed class DashboardScreenEvent {
    data class OnDarkThemeToggle(val darkTheme: Boolean): DashboardScreenEvent()
}