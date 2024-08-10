package com.sendiko.librariestesting.contentbox

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.Message
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sendiko.content_box_with_notification.ContentBoxWithNotification
import com.sendiko.librariestesting.contentbox.component.ColorOfCard
import com.sendiko.librariestesting.contentbox.component.MenuCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentBoxDemoScreen(
    modifier: Modifier = Modifier,
    state: ContentBoxDemoState,
    onEvent: (ContentBoxDemoScreenEvent) -> Unit,
    onNavigate: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val loadingColor = ColorOfCard(
        containerColor = if (state.isLoading) Color.Blue else Color.Transparent,
        contentColor = if (state.isLoading) Color.White else CardDefaults.cardColors().contentColor
    )
    val errorColor = ColorOfCard(
        containerColor = if (state.isError) MaterialTheme.colorScheme.error else Color.Transparent,
        contentColor = if (state.isError) MaterialTheme.colorScheme.onError else CardDefaults.cardColors().contentColor
    )
    val notificationColor = ColorOfCard(
        containerColor = if (state.notificationMessage == "Notification Example.") MaterialTheme.colorScheme.tertiaryContainer else Color.Transparent,
        contentColor = if (state.notificationMessage == "Notification Example.") MaterialTheme.colorScheme.onTertiaryContainer else CardDefaults.cardColors().contentColor
    )
    ContentBoxWithNotification(
        message = state.notificationMessage,
        isLoading = state.isLoading,
        isErrorNotification = state.isError,
        content = {
            Scaffold(
                topBar = {
                    LargeTopAppBar(
                        scrollBehavior = scrollBehavior,
                        title = { Text(text = "ContentBoxWithNotification") },
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
                            text = "Description",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(text = "The ContentBoxWithNotification will provide a box that pops down from the top of the screen based on the current screen's state. Use the menu below to try out.")
                    }
                    item {
                        Text(
                            text = "Menu",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            MenuCard(
                                color = loadingColor,
                                text = "Set Loading",
                                icon = Icons.Rounded.Refresh,
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    onEvent(ContentBoxDemoScreenEvent.OnSetLoading(!state.isLoading))
                                }
                            )
                            MenuCard(
                                color = errorColor,
                                text = "Set Error",
                                icon = Icons.Rounded.Error,
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    onEvent(ContentBoxDemoScreenEvent.OnSetError(!state.isError))
                                }
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            MenuCard(
                                color = notificationColor,
                                text = "Set Message",
                                icon = Icons.AutoMirrored.Rounded.Message,
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    onEvent(ContentBoxDemoScreenEvent.OnSetMessage)
                                }
                            )
                            MenuCard(
                                color = ColorOfCard(containerColor = MaterialTheme.colorScheme.surface, contentColor = MaterialTheme.colorScheme.onSurface),
                                text = "Clear State",
                                icon = Icons.Rounded.Clear,
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    onEvent(ContentBoxDemoScreenEvent.ClearState)
                                }
                            )
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
private fun ContentBoxDemoScreenPrev() {
    ContentBoxDemoScreen(state = ContentBoxDemoState(), onEvent = {}) {

    }
}