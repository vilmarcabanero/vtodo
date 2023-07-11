package com.entalpiya.app.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.entalpiya.app.R
import com.entalpiya.app.core.presentation.ui.theme.EntalpiyaTheme
import com.entalpiya.app.index.presentation.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Entalpiya)
        setContent {
            EntalpiyaTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}
