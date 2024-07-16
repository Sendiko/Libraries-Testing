package com.sendiko.librariestesting.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit
) {
    Scaffold(
        topBar = {
            LargeTopAppBar(
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
        Column(
            modifier = modifier.padding(it),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            content = {
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
                    Text(text = "1.0.0", fontWeight = FontWeight.Bold)
                }
            }
        )
    }
}