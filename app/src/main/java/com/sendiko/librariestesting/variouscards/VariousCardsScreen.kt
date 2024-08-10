@file:OptIn(ExperimentalMaterial3Api::class)

package com.sendiko.librariestesting.variouscards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.LightMode
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
import com.sendiko.variouscard.elevated.ElevatedMenuCard
import com.sendiko.variouscard.outline.OutlinedMenuCard
import com.sendiko.variouscard.selectable.SelectableMenuCard

@Composable
fun VariousCardScreen(
    modifier: Modifier = Modifier,
    state: VariousCardsState,
    onEvent: (VariousCardsEvent) -> Unit,
    onNavigateBack: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        topBar = {
            LargeTopAppBar(
                scrollBehavior = scrollBehavior,
                title = { Text(text = "VariousCards") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "back"
                        )
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
                    text = "Description",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Various Cards provides a bunch of card variant that can be used as Menu component, or even Selectable component, many variants are coming soon!"
                )
            }
            item {
                Text(
                    text = "Example Elevated Variant",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    ElevatedMenuCard(
                        onClick = { /*TODO*/ },
                        icon = {
                            Icon(
                                imageVector = Icons.Rounded.Bookmark,
                                contentDescription = "bookmark"
                            )
                        },
                        title = "Bookmark",
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    ElevatedMenuCard(
                        onClick = { /*TODO*/ },
                        icon = {
                            Icon(
                                imageVector = Icons.Rounded.History,
                                contentDescription = "history"
                            )
                        },
                        title = "History",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            item {
                Text(
                    text = "Example Outlined Variant",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedMenuCard(
                        onClick = { /*TODO*/ },
                        icon = {
                            Icon(
                                imageVector = Icons.Rounded.Bookmark,
                                contentDescription = "bookmark"
                            )
                        },
                        title = "Bookmark",
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    OutlinedMenuCard(
                        onClick = { /*TODO*/ },
                        icon = {
                            Icon(
                                imageVector = Icons.Rounded.History,
                                contentDescription = "history"
                            )
                        },
                        title = "History",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            item {
                Text(
                    text = "Example Selectable Variant",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SelectableMenuCard(
                        onClick = { onEvent(VariousCardsEvent.OnSelectCard(1)) },
                        icon = {
                            Icon(
                                imageVector = Icons.Rounded.LightMode,
                                contentDescription = "bookmark"
                            )
                        },
                        title = "Light Mode",
                        modifier = Modifier.weight(1f),
                        isSelected = state.isSelected.contains(1)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    SelectableMenuCard(
                        onClick = { onEvent(VariousCardsEvent.OnSelectCard(2)) },
                        icon = {
                            Icon(
                                imageVector = Icons.Rounded.DarkMode,
                                contentDescription = "history"
                            )
                        },
                        title = "Dark Mode",
                        modifier = Modifier.weight(1f),
                        isSelected = state.isSelected.contains(2)
                    )
                }
            }
        }
    }
}