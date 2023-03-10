package com.ux.thecolab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.ux.thecolab.ui.theme.TheColabTheme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheColabTheme {
                val navController = rememberNavController()
                Scaffold(
                ){ innerPadding ->
                    ColabNavHost(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )

                }
            }
        }
    }
}
