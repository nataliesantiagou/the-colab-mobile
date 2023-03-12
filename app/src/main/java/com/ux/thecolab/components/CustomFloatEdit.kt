package com.ux.thecolab.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomFloatEdit (
    containerColor: Color,
    contentColor: Color,
    onClick : () -> Unit = {},
) {
    FloatingActionButton(onClick = onClick ,
        containerColor = containerColor,
        shape = RoundedCornerShape(50.dp),
    ) {
        Icon(
            imageVector = Icons.Rounded.Edit,
            contentDescription = "Add FAB",
            tint = contentColor,
        )
    }
}
