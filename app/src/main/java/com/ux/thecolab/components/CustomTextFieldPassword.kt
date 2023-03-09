package com.ux.thecolab.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.ux.thecolab.R
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier

@Composable
fun CustomTextFieldPassword (
    unfocusedColor: Color,
    focusedColor: Color,
    primaryColor: Color,
    text: String,
) {
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    val icon = if (passwordVisibility)
        painterResource(id = R.drawable.hidde_password)
    else
        painterResource(id = R.drawable.view_password)

    OutlinedTextField(
        value = password,
        onValueChange = {
            password = it
        },
        label = { Text(text = text) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = unfocusedColor,
            unfocusedLabelColor = unfocusedColor,
            focusedBorderColor = focusedColor,
            focusedLabelColor = focusedColor,
            textColor = primaryColor
        ),
        trailingIcon = {
            IconButton(onClick = {
                passwordVisibility = !passwordVisibility
            }) {
                Icon(
                    painter = icon,
                    contentDescription = "Visibility Icon",
                    modifier = Modifier
                        .size(15.dp),
                    tint = primaryColor
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = if (passwordVisibility) VisualTransformation.None
        else PasswordVisualTransformation()
    )
}