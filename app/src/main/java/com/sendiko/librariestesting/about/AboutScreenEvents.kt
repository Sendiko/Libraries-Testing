package com.sendiko.librariestesting.about

sealed class AboutScreenEvents {
    data class OnThemeBaseToggle(val themeBasedSystem: Boolean): AboutScreenEvents()
    data class OnDarkThemeToggle(val isDarkTheme: Boolean): AboutScreenEvents()
}