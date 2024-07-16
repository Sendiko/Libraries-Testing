package com.sendiko.librariestesting.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sendiko.librariestesting.dashboard.components.LibraryCard
import com.sendiko.librariestesting.dashboard.components.LibraryData
import com.sendiko.librariestesting.dashboard.components.ModuleName
import com.sendiko.librariestesting.dashboard.components.Name
import com.sendiko.librariestesting.dashboard.components.Version
import com.sendiko.librariestesting.navigation.AboutScreen
import com.sendiko.librariestesting.navigation.ContentBoxScreen
import com.sendiko.librariestesting.navigation.MultipleTextFieldScreen
import com.sendiko.librariestesting.navigation.SelectorScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    onNavigate: (destination: Any) -> Unit
) {
    val libraries = listOf(
        LibraryData(
            name = Name("ContentBoxWithNotification"),
            moduleName = ModuleName("com.github.Sendiko:content-box-with-notification"),
            version = Version("1.0.0"),
            destination = ContentBoxScreen
        ),
        LibraryData(
            name = Name("Selector"),
            moduleName = ModuleName("com.github.Sendiko:selector-component"),
            version = Version("1.0.2"),
            destination = SelectorScreen
        ),
        LibraryData(
            name = Name("MultipleTextField"),
            moduleName = ModuleName("com.github.Sendiko:multiple-textfield"),
            version = Version("1.0.0"),
            destination = MultipleTextFieldScreen
        ),
    )
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = { Text(text = "Library Testing App") },
                actions = {
                    IconButton(
                        onClick = { onNavigate(AboutScreen) },
                        content = {
                            Icon(
                                imageVector = Icons.Rounded.Info,
                                contentDescription = "Info"
                            )
                        }
                    )
                }
            )
        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(
                top = it.calculateTopPadding(),
                start = 16.dp,
                end = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(libraries) { library ->
                LibraryCard(
                    data = library,
                    onClick = {
                        onNavigate(library.destination)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun DashboardScreenPrev() {
    DashboardScreen {

    }
}