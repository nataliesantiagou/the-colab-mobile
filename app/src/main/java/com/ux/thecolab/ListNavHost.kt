package com.ux.thecolab

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ux.thecolab.ui.AlarmListScreen
import com.ux.thecolab.ui.CreatePatientScreen
import com.ux.thecolab.ui.PatientsListScreen

@Composable
fun ListNavHost(
    navController: NavHostController,
    navControllerRoot: NavHostController,
    modifier: Modifier = Modifier,
    showSnackbar: (String, SnackbarDuration) -> Unit
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
                navController.navigate(CreatePatient.route)
            })
        }

        composable(route = CreatePatient.route) {
            CreatePatientScreen(
                onClickCreate = {
                    navController.navigate(route = PatientsList.route)
                },
                goBack = {
                    navController.popBackStack()
                },
                showSnackbar = showSnackbar
            )
        }
    }
}