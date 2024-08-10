package com.sendiko.librariestesting.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    state: AboutScreenState,
    onEvent: (AboutScreenEvents) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = {
            LargeTopAppBar(
                scrollBehavior = scrollBehavior,
                title = { Text(text = "About") },
                navigationIcon = {
                    IconButton(onClick = onNavigate) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            contentPadding = it,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = "Preferences",
                    style = MaterialTheme.typography.labelLarge
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "Dark Theme",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Toggle to change app theme to dark theme",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Switch(
                        checked = state.isDarkTheme,
                        onCheckedChange = {
                            onEvent(AboutScreenEvents.OnDarkThemeToggle(it))
                        },
                        enabled = !state.isThemeBasedSystem,
                        thumbContent = {
                            if (state.isDarkTheme) {
                                Icon(
                                    modifier = Modifier.padding(4.dp),
                                    imageVector = Icons.Rounded.DarkMode,
                                    contentDescription = "DarkMode"
                                )
                            } else {
                                Icon(
                                    modifier = Modifier.padding(4.dp),
                                    imageVector = Icons.Rounded.LightMode,
                                    contentDescription = "LightMode"
                                )
                            }
                        }
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "Theme Based System",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Toggle to change app theme based on system settings",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Switch(
                        checked = state.isThemeBasedSystem,
                        onCheckedChange = {
                            onEvent(AboutScreenEvents.OnThemeBaseToggle(it))
                        }
                    )
                }
            }
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = "About",
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = "Introducing my demo app, designed to showcase the capabilities of my custom Jetpack Compose libraries. This app highlights modern Android development techniques, demonstrating intuitive UI building, seamless state management with MVI and Clean Architecture, and efficient data handling with Room Database and Datastore Preferences. Ideal for developers at any level, this app provides practical examples and insights into mastering Jetpack Compose.",
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Version: ")
                    Text(text = "1.2.0", fontWeight = FontWeight.Bold)
                }
            }

        }
    }
}