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
}

/**
 * Colab app navigation destinations
 */
object Root : ColabDestination {
    override val icon = Icons.Filled.Email
    override val route = "root"
}

object Register : ColabDestination {
    override val icon = Icons.Filled.Person
    override val route = "register"
}

object Recover : ColabDestination {
    override val icon = Icons.Filled.Person
    override val route = "recover"
}

object AlarmList : ColabDestination {
    override val icon = Icons.Filled.Person
    override val route = "alarm-list"
}

object PatientsList : ColabDestination {
    override val icon = Icons.Filled.Person
    override val route = "patients"
}

object CreatePatient : ColabDestination {
    override val icon = Icons.Filled.Person
    override val route = "create-patients"
}

// Screens to be displayed in the top ColabTabRow
val colabTabRowScreens = listOf(Root)
