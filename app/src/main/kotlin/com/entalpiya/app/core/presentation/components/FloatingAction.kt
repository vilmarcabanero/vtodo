package com.entalpiya.app.core.presentation.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FloatingAction(
    onClick: () -> Unit,
    icon: ImageVector,
    desc: String,
) {
    FloatingActionButton(
        onClick = {
            onClick()
        },
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Icon(imageVector = icon, contentDescription = desc)
    }
}

@Preview
@Composable
fun FloatingActionPreview() {
    Surface {
        FloatingAction(onClick = {}, icon = Icons.Default.Add, desc = "add")
    }
}