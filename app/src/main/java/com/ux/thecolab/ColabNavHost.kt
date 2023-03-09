package com.ux.thecolab

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ux.thecolab.ui.AlbumListScreen
import com.ux.thecolab.ui.RecoverScreen
import com.ux.thecolab.ui.RegisterScreen
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
            RootScreen(
                onClickRegister = {
                    navController.navigate(route = Register.route)
                },
                onClickRecover = {
                    navController.navigate(route = Recover.route)
                },
                onClickLogin = {
                    navController.navigate(route = AlarmList.route)
                }
            )
        }
        composable(route = Register.route) {
            RegisterScreen(
                onClickRoot = {
                    navController.navigate(route = Root.route)
                },
                onClickRegister = {
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
            AlbumListScreen(

            )
        }
    }
}