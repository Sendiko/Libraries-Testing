package com.sendiko.librariestesting.selector

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.sendiko.selector_component.Selector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectorDemoScreen(
    modifier: Modifier = Modifier,
    state: SelectorDemoScreenState,
    onEvent: (SelectorDemoScreenEvent) -> Unit,
    onNavigate: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        topBar = {
            LargeTopAppBar(
                scrollBehavior = scrollBehavior,
                title = { Text(text = "Selector") },
                navigationIcon = {
                    IconButton(onClick = onNavigate) {
                        Icon(imageVector = Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "back")
                    }
                }
            )
        }
    ) {
        val padding = PaddingValues(
            top = it.calculateTopPadding(),
            start = 16.dp,
            end = 16.dp
        )
        LazyColumn(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Example",
                    style = MaterialTheme.typography.titleMedium
                )
                Selector(
                    currentSelected = state.currentlySelected,
                    items = state.items,
                    onSelect = { data ->
                        onEvent(SelectorDemoScreenEvent.OnSelect(data))
                    },
                )
            }
            item {
                Text(
                    text = "Description",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(text = "The Selector lets user know that there are other options to see in the current screen.")
            }
        }
    }
}