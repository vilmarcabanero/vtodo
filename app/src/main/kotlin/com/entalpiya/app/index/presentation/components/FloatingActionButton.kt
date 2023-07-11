package com.entalpiya.app.index.presentation.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FloatingAction(
    navigateTo: () -> Unit,
    icon: ImageVector,
    desc: String,
) {
    FloatingActionButton(
        onClick = {
            navigateTo()
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
        FloatingAction(navigateTo = {}, icon = Icons.Default.Add, desc = "add")
    }
}