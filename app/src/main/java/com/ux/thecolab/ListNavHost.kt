package com.ux.thecolab

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ux.thecolab.ui.AlarmListScreen
import com.ux.thecolab.ui.CreatePatientScreen
import com.ux.thecolab.ui.PatientsListScreen
import com.ux.thecolab.ui.createAlarm.CreateAlarmScreen
import com.ux.thecolab.ui.detailAlarm.DetailAlarmScreen

@Composable
fun ListNavHost(
    navController: NavHostController,
    navControllerRoot: NavHostController,
    modifier: Modifier = Modifier,
    toggleBar: (Boolean) -> Unit,
    toggleVisibleTopBar: (Boolean) -> Unit,
    showSnackbar: (String, SnackbarDuration) -> Unit,
    isAlarmCreated: MutableState<Boolean>,
    togglelarmCreated: (Boolean) -> Unit,
) {
    NavHost(
        navController = navController, startDestination = AlarmList.route, modifier = modifier
    ) {
        composable(route = AlarmList.route) {
            AlarmListScreen(onClickCreate = {
                navController.navigate(CreateAlarm.route)
            }, goPatient = {
                navController.navigate(PatientsList.route)
            },
            isAlarmCreated = isAlarmCreated,
            onClickDetail = {
                navController.navigate(DetailAlarm.route)
            })
        }

        composable(route = PatientsList.route) {
            PatientsListScreen(onClickCreate = {
                navController.navigate(CreatePatient.route)
            })
        }

        composable(route = CreatePatient.route) {
            CreatePatientScreen(onClickCreate = {
                navController.navigate(route = PatientsList.route)
            }, goBack = {
                navController.popBackStack()
            }, showSnackbar = showSnackbar
            )
        }

        composable(route = CreateAlarm.route) {
            CreateAlarmScreen(onClickCreate = {
                navController.navigate(route = AlarmList.route)
            }, goBack = {
                navController.popBackStack()
            }, showSnackbar = showSnackbar,
                toggleBar = toggleBar,
                toggleVisibleTopBar = toggleVisibleTopBar,
                togglelarmCreated = togglelarmCreated
            )
        }

        composable(route = DetailAlarm.route) {
            DetailAlarmScreen()
        }
    }
}