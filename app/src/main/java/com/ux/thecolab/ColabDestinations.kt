package com.ux.thecolab

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person

/**
 * Contract for information needed on every Colab navigation destination
 */
interface ColabDestination {
    val icon: ImageVector
    val route: String
    val label: String
}

/**
 * Colab app navigation destinations
 */
object Root : ColabDestination {
    override val icon = Icons.Filled.Email
    override val route = "root"
    override val label = "root"
}

object Register : ColabDestination {
    override val icon = Icons.Filled.Person
    override val route = "register"
    override val label = "register"
}

object Recover : ColabDestination {
    override val icon = Icons.Filled.Person
    override val route = "recover"
    override val label = "recover"
}

object AlarmList : ColabDestination {
    override val icon = Icons.Filled.Person
    override val route = "alarm-list"
    override val label = "Recordatorios"
}

object PatientsList : ColabDestination {
    override val icon = Icons.Filled.Person
    override val route = "patients"
    override val label = "Pacientes"
}

object CreatePatient : ColabDestination {
    override val icon = Icons.Filled.Person
    override val route = "create-patients"
    override val label = "create-patients"
}

object Home : ColabDestination {
    override val icon = Icons.Filled.Person
    override val route = "home"
    override val label = "home"
}

object CreateAlarm : ColabDestination {
    override val icon = Icons.Filled.Person
    override val route = "create-alarm"
    override val label = "create-alarm"
}

object DetailAlarm : ColabDestination {
    override val icon = Icons.Filled.Person
    override val route = "detail-alarm"
    override val label = "detail-alarm"
}

// Screens to be displayed in the top ColabTabRow
val colabTabRowScreens = listOf(AlarmList, PatientsList)
