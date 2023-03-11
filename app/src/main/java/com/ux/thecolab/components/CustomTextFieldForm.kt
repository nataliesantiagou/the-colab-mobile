package com.ux.thecolab.components

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@Composable
fun CustomTextFieldForm (
    unfocusedColor: Color,
    focusedColor: Color,
    primaryColor: Color,
    text: String,
    value: String,
    onValueChange : (String) -> Unit = {},
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(text) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = unfocusedColor,
            unfocusedLabelColor = unfocusedColor,
            focusedBorderColor = focusedColor,
            focusedLabelColor = focusedColor,
            textColor = primaryColor
        ),

    )
}