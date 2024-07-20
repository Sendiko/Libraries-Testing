package com.sendiko.librariestesting.varioustextfields

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.TextSnippet
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Lock
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
import com.sendiko.librariestesting.varioustextfields.VariousTextFieldScreenEvent.OnPasswordToggle
import com.sendiko.librariestesting.varioustextfields.VariousTextFieldScreenEvent.OnRoundedTextFieldTextChange
import com.sendiko.varioustextfields.MultipleTextField
import com.sendiko.varioustextfields.RoundedPasswordTextField
import com.sendiko.varioustextfields.RoundedTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VariousTextFieldScreen(
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    state: VariousTextFieldScreenState,
    onEvent: (VariousTextFieldScreenEvent) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        topBar = {
            LargeTopAppBar(
                scrollBehavior = scrollBehavior,
                title = { Text(text = "VariousTextField") },
                navigationIcon = {
                    IconButton(onClick = onNavigate) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
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
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Description",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(text = "RoundedTextField gives a beautiful Outlined Text Field with rounded corners and options for customization!")
                }
            }
            item {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "RoundedTextField",
                        style = MaterialTheme.typography.titleMedium
                    )
                    RoundedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = state.roundedTextFieldText,
                        leadingIcon = Icons.AutoMirrored.Rounded.TextSnippet,
                        trailingIcon = Icons.Rounded.Clear,
                        onValueChange = { text -> onEvent(OnRoundedTextFieldTextChange(text)) }
                    )
                }
            }
            item {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                )  {
                    Text(
                        text = "RoundedPasswordTextField",
                        style = MaterialTheme.typography.titleMedium
                    )
                    RoundedPasswordTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = state.roundedTextFieldText,
                        onValueChange = { text -> onEvent(OnRoundedTextFieldTextChange(text)) },
                        isPasswordVisible = state.passwordVisible,
                        leadingIcon = Icons.Rounded.Lock,
                        onPasswordToggle = { visible -> onEvent(OnPasswordToggle(visible)) }
                    )
                }
            }
            item {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                )  {
                    Text(
                        text = "MultipleTextField",
                        style = MaterialTheme.typography.titleMedium
                    )
                    MultipleTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = state.multipleTextFieldText,
                        numberOfTextField = 4
                    )
                }
            }
        }
    }
}