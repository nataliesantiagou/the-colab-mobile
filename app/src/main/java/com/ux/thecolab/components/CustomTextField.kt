package com.ux.thecolab.components

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@Composable
fun CustomTextField (
    unfocusedColor: Color,
    focusedColor: Color,
    primaryColor: Color,
    text: String,
) {
    var name by remember { mutableStateOf("") }
    OutlinedTextField(
        value = name,
        onValueChange = { name = it },
        label = { Text(text) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = unfocusedColor,
            unfocusedLabelColor = unfocusedColor,
            focusedBorderColor = focusedColor,
            focusedLabelColor = focusedColor,
            textColor = primaryColor
        )
    )
}