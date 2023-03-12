package com.ux.thecolab

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ux.thecolab.ui.*
import com.ux.thecolab.ui.showAlarm.ShowAlarmScreen

@Composable
fun ColabNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    showSnackbar: (String, SnackbarDuration) -> Unit
) {
    val alarmCreated = remember {
        mutableStateOf(false)
    }
    NavHost(
        navController = navController,
        startDestination = Root.route,
        modifier = modifier
    ) {
        composable(route = Root.route) {
            RootScreen(
                onClickRegister = {
                    navController.navigate(route = Register.route)
                },
                onClickRecover = {
                    navController.navigate(route = Recover.route)
                },
                onClickLogin = {
                    navController.navigate(route = Home.route)
                }
            )
        }
        composable(route = Register.route) {
            RegisterScreen(
                onClickRoot = {
                    navController.navigate(route = Root.route)
                },
                showSnackbar = showSnackbar
            )
        }
        composable(route = Recover.route) {
            RecoverScreen(
                onClickRoot = {
                    navController.navigate(route = Root.route)
                }
            )
        }
        composable(route = Home.route) {
            HomeScreen(navControllerRoot = navController, alarmCreated=alarmCreated)
        }
        composable(route = ShowAlarm.route) {
            ShowAlarmScreen(
                onClickClose = {
                    navController.navigate(route = Home.route)
                }
            )
        }
    }
}