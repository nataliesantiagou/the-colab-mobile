package com.ux.thecolab

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ux.thecolab.ui.RootScreen

@Composable
fun ColabNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Root.route,
        modifier = modifier
    ) {
        composable(route = Root.route) {
            RootScreen()
        }
    }
}