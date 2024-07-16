package com.sendiko.librariestesting.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LibraryCard(
    modifier: Modifier = Modifier,
    data: LibraryData,
    onClick: (data: LibraryData) -> Unit
) {
    OutlinedCard(
        modifier = modifier,
        onClick = { onClick(data) }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = data.name.name,
                        fontSize = 16.sp
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.tertiary)
                    ) {
                        Text(
                            modifier = Modifier.padding(4.dp),
                            text = data.version.version,
                            color = MaterialTheme.colorScheme.onTertiary
                        )
                    }
                }
                Text(
                    text = data.moduleName.moduleName,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowRight,
                contentDescription = "Next",
                modifier = Modifier.size(48.dp)
            )
        }
    }
}

@Preview
@Composable
private fun LibraryCardPrev() {
    Surface {
        val data = LibraryData(
            name = Name("ContentBoxWithNotification"),
            moduleName = ModuleName("com.github.Sendiko:content-box-with-notification"),
            version = Version("1.0.0"),
            destination = ""
        )
        LibraryCard(
            data = data,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {

        }
    }
}