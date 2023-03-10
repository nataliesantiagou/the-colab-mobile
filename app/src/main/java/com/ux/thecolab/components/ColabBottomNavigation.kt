package com.ux.thecolab.components
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ux.thecolab.ColabDestination

@Composable
fun ColabBottomNavigation(
    allScreens: List<ColabDestination>,
    onTabSelected: (ColabDestination) -> Unit,
    currentScreen: ColabDestination
) {
    Row(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxWidth()
    ) {
        allScreens.forEach { screen ->
            AddItem(
                label = screen.label,
                onSelected = { onTabSelected(screen) },
                selected = currentScreen == screen
            )
        }
    }
}


@Composable
fun RowScope.AddItem(label: String, selected: Boolean, onSelected : () -> Unit = {}) {
    val whiteColor: Color = MaterialTheme.colorScheme.onSecondary
    val tertiaryColor: Color = MaterialTheme.colorScheme.tertiary

    val background =
        if (selected) tertiaryColor else tertiaryColor.copy(alpha = 0.8f)

    val contentColor =
        if (selected) whiteColor else whiteColor.copy(alpha = 0.8f)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .weight(1f)
            .background(background)
            .clickable(onClick = onSelected)
    ) {


        Row(
            modifier = Modifier
                .align(alignment = Alignment.Center),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ) {
            Text(
                text = label,
                color = contentColor,
                fontSize = 12.sp
            )
        }
    }
}
