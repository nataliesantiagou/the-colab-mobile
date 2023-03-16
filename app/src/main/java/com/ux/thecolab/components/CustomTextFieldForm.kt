package com.ux.thecolab.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextFieldForm (
    unfocusedColor: Color,
    focusedColor: Color,
    primaryColor: Color,
    text: String,
    value: String,
    onValueChange : (String) -> Unit = {},
    haveTooltip: Boolean = false
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
        modifier = Modifier.fillMaxWidth()
    )
}