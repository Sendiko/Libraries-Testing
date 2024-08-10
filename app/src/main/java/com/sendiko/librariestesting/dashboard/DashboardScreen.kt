package com.sendiko.librariestesting.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.sendiko.librariestesting.dashboard.components.LibraryCard
import com.sendiko.librariestesting.dashboard.components.LibraryData
import com.sendiko.librariestesting.dashboard.components.ModuleName
import com.sendiko.librariestesting.dashboard.components.Name
import com.sendiko.librariestesting.dashboard.components.Version
import com.sendiko.librariestesting.navigation.AboutScreen
import com.sendiko.librariestesting.navigation.ContentBoxScreen
import com.sendiko.librariestesting.navigation.SelectorScreen
import com.sendiko.librariestesting.navigation.VariousCardScreen
import com.sendiko.librariestesting.navigation.VariousTextFieldScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    onNavigate: (destination: Any) -> Unit,
    state: DashboardScreenState,
    onEvent: (event: DashboardScreenEvent) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
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
            version = Version("1.0.34"),
            destination = SelectorScreen
        ),
        LibraryData(
            name = Name("Various TextField"),
                moduleName = ModuleName( "com.github.Sendiko:various-textfield:"),
            version = Version("1.0.3"),
            destination = VariousTextFieldScreen
        ),
        LibraryData(
            name = Name("Various Cards"),
            moduleName = ModuleName( "com.github.Sendiko:various-cards:"),
            version = Version("1.0.1"),
            destination = VariousCardScreen
        ),
    )
    Scaffold(
        topBar = {
            LargeTopAppBar(
                scrollBehavior = scrollBehavior,
                title = { Text(text = "Library Testing App") },
                actions = {
                    IconButton(
                        onClick = { onEvent(DashboardScreenEvent.OnDarkThemeToggle(!state.isDarkTheme)) },
                        content = {
                            Icon(
                                imageVector = if (!state.isDarkTheme) Icons.Rounded.LightMode else Icons.Rounded.DarkMode,
                                contentDescription = "Info"
                            )
                        },
                        enabled = !state.isThemeBasedSystem
                    )
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
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
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