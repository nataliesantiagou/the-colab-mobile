package com.ux.thecolab

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.filled.Email

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

// Screens to be displayed in the top ColabTabRow
val colabTabRowScreens = listOf(Root)
