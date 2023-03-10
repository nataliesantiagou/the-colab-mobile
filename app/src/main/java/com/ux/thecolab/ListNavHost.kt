package com.ux.thecolab

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ux.thecolab.ui.AlarmListScreen
import com.ux.thecolab.ui.PatientsListScreen

@Composable
fun ListNavHost(
    navController: NavHostController,
    navControllerRoot: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AlarmList.route,
        modifier = modifier
    ) {
        composable(route = AlarmList.route) {
            AlarmListScreen(onClickCreate = {
                navControllerRoot.navigate(CreatePatient.route)
            })
        }
        composable(route = PatientsList.route) {
            PatientsListScreen(onClickCreate = {
                navControllerRoot.navigate(CreatePatient.route)
            })
        }
    }
}