package com.ux.thecolab

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ux.thecolab.ui.*

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
                }
            )
        }
        composable(route = Recover.route) {
            RecoverScreen(
                onClickRoot = {
                    navController.navigate(route = Root.route)
                }
            )
        }
        composable(route = AlarmList.route) {
            AlarmListScreen(
                onClickCreate = {
                    navController.navigate(route = Root.route)
                }
            )
        }
        composable(route = PatientsList.route) {
            PatientsListScreen(
                onClickCreate = {
                    navController.navigate(route = CreatePatient.route)
                }
            )
        }
        composable(route = CreatePatient.route) {
            CreatePatientScreen(
                onClickCreate = {
                    navController.navigate(route = Root.route)
                }
            )
        }
        composable(route = Home.route) {
            HomeScreen(navControllerRoot = navController)
        }
    }
}