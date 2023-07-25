package com.entalpiya.app.core.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.entalpiya.app.core.presentation.ui.theme.EntalpiyaTheme
import com.entalpiya.app.feature_home.presentation.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost

@Composable
fun EntalpiyaApp() {
    EntalpiyaTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            DestinationsNavHost(navGraph = NavGraphs.root)
        }
    }
}