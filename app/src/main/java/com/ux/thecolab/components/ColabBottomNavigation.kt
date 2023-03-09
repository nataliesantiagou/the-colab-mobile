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

@Composable
fun ColabBottomNavigation() {
    BottomBar()
}

@Composable
fun BottomBar() {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Artists", "Playlists")

    Row(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp)
            .background(Color.Transparent)
            .fillMaxWidth()
    ) {
        items.forEachIndexed { index, item ->
            AddItem(
                label = item,
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}

@Composable
fun RowScope.AddItem(label: String, selected: Boolean, onClick : () -> Unit = {}) {
    val background =
        if (selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.6f) else Color.Transparent

    val contentColor =
        if (selected) Color.Red else Color.Black
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .weight(1f)
            .background(background)
            .clickable(onClick = onClick)
    ) {


        Row(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp)
                .align(alignment = Alignment.Center),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ) {
            Text(
                text = label,
                color = contentColor
            )
        }
    }
}

@Composable
@Preview
fun BottomNavPreview() {
    ColabBottomNavigation()
}