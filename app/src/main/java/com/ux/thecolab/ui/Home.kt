package com.ux.thecolab.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ux.thecolab.AlarmList
import com.ux.thecolab.CreatePatient
import com.ux.thecolab.ListNavHost
import com.ux.thecolab.colabTabRowScreens
import com.ux.thecolab.components.ColabBottomNavigation
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navControllerRoot: NavHostController) {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen =
        colabTabRowScreens.find { it.route == currentDestination?.route } ?: AlarmList

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(bottomBar = {
        ColabBottomNavigation(
            allScreens = colabTabRowScreens,
            onTabSelected = { newScreen ->
                navController.navigate(newScreen.route)
            },
            currentScreen = currentScreen,
            visible = CreatePatient.route != currentDestination?.route
        )
    },
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
                        ) { Text(data.visuals.actionLabel ?: "", color = MaterialTheme.colorScheme.tertiary) }
                    },

                    ) {
                    Text(data.visuals.message, color = MaterialTheme.colorScheme.tertiary)
                }
            }
        }
    ) { innerPadding ->
        ListNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            navControllerRoot = navControllerRoot,
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


