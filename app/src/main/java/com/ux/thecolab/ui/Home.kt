package com.ux.thecolab.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ux.thecolab.*
import com.ux.thecolab.components.ColabBottomNavigation
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.log


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navControllerRoot: NavHostController, alarmCreated: MutableState<Boolean>) {
    val navController = rememberNavController()

    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen =
        colabTabRowScreens.find { it.route == currentDestination?.route } ?: AlarmList

    val snackbarHostState = remember { SnackbarHostState() }
    val btnBar = remember {
        mutableStateOf(true)
    }

    val visibleTopBar = remember {
        mutableStateOf(true)
    }


    val scope = rememberCoroutineScope()

    Scaffold(
        bottomBar = {
        ColabBottomNavigation(
            allScreens = colabTabRowScreens,
            onTabSelected = { newScreen ->
                navController.navigate(newScreen.route)
            },
            currentScreen = currentScreen,
            visible = btnBar.value
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
        },
        topBar = {
            if (visibleTopBar.value) {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
                    ),
                    title = {
                        Text(
                            "THE COLAB",
                            maxLines = 1,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSecondary,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    navigationIcon = {
                        if (CreatePatient.route == currentDestination?.route || CreateAlarm.route == currentDestination?.route || EditAlarm.route == currentDestination?.route || DetailAlarm.route == currentDestination?.route) {
                            IconButton(onClick = { navController.popBackStack() }, colors = IconButtonDefaults.iconButtonColors(
                                contentColor = MaterialTheme.colorScheme.onSecondary
                            )) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Localized description",
                                )
                            }
                        }
                    }
                )
            }
        },
    ) { innerPadding ->
        ListNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            showSnackbar = { message, duration ->
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = message, duration = duration, actionLabel = "Cerrar"
                    )
                }
            },
            toggleBar = {
                btnBar.value = it
            },
            isAlarmCreated = alarmCreated,
            togglelarmCreated = {
                alarmCreated.value = it
                scope.launch {
                    delay(2000)
                    navControllerRoot.navigate(ShowAlarm.route)
                }
            },
            toggleVisibleTopBar = {
                visibleTopBar.value = it
            }
        )

    }
}


