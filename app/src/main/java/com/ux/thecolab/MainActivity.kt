package com.ux.thecolab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ux.thecolab.ui.theme.TheColabTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheColabTheme {
                val navController = rememberNavController()

                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()

                Scaffold(
                    snackbarHost = {
                        // reuse default SnackbarHost to have default animation and timing handling
                        SnackbarHost(snackbarHostState) { data ->
                            Snackbar(
                                containerColor = MaterialTheme.colorScheme.onTertiary,
                                modifier = Modifier
                                    .padding(12.dp),
                                action = {
                                    TextButton(
                                        onClick = { data.dismiss() }
                                    ) {
                                        Text(
                                            data.visuals.actionLabel ?: "",
                                            color = MaterialTheme.colorScheme.tertiary
                                        )
                                    }
                                },

                                ) {
                                Text(data.visuals.message, color = MaterialTheme.colorScheme.tertiary)
                            }
                        }
                    }
                ){ innerPadding ->
                    ColabNavHost(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding),
                        showSnackbar = { message, duration ->
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = message, duration = duration, actionLabel = "Cerrar"
                                )
                            }
                        }
                    )

                }
            }
        }
    }
}
