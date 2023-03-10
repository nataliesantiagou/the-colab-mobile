package com.ux.thecolab.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ux.thecolab.AlarmList
import com.ux.thecolab.ListNavHost
import com.ux.thecolab.colabTabRowScreens
import com.ux.thecolab.components.ColabBottomNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navControllerRoot: NavHostController) {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen =
        colabTabRowScreens.find { it.route == currentDestination?.route } ?: AlarmList

    Scaffold(bottomBar = {
        ColabBottomNavigation(
            allScreens = colabTabRowScreens,
            onTabSelected = { newScreen ->
                navController.navigate(newScreen.route)
            },
            currentScreen = currentScreen
        )
    }) { innerPadding ->
        ListNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            navControllerRoot = navControllerRoot
        )

    }
}
