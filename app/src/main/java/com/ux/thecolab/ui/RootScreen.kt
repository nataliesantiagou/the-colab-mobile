package com.ux.thecolab.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ux.thecolab.R
import com.ux.thecolab.components.CustomButton
import com.ux.thecolab.components.CustomTextField
import com.ux.thecolab.components.CustomTextFieldPassword

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootScreen(
    onClickRegister: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight(),
        contentAlignment = Alignment.TopCenter
    ) {
        val focusedColor: Color = MaterialTheme.colorScheme.onPrimary
        val unfocusedColor: Color = MaterialTheme.colorScheme.secondary
        val primaryColor: Color = MaterialTheme.colorScheme.primary
        val whiteColor: Color = MaterialTheme.colorScheme.onSecondary

        Image(
            contentScale = ContentScale.FillHeight,
            painter = painterResource(id = R.drawable.fondo_pagina_principal),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.padding(40.dp))
            Text(text = "THE COLAB", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = primaryColor)

            Spacer(modifier = Modifier.padding(25.dp))
            Image(
                contentScale = ContentScale.FillHeight,
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(126.dp)
            )

            Spacer(modifier = Modifier.padding(15.dp))
            CustomTextField(unfocusedColor = unfocusedColor, focusedColor = focusedColor, primaryColor = primaryColor, text = "Nombre de usuario")

            Spacer(modifier = Modifier.padding(10.dp))
            CustomTextFieldPassword(unfocusedColor = unfocusedColor, focusedColor = focusedColor, primaryColor = primaryColor, text = "Contraseña")

            Spacer(modifier = Modifier.padding(10.dp))
            CustomButton(containerColor = primaryColor, contentColor = whiteColor, text = "Iniciar sesión", route = "")

            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = "O", fontSize = 14.sp, fontWeight = FontWeight.Normal, color = primaryColor)

            Spacer(modifier = Modifier.padding(5.dp))
            CustomButton(containerColor = primaryColor, contentColor = whiteColor, text = "Crear cuenta", route = "")

            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = "Recuperar contraseña", fontSize = 14.sp, fontWeight = FontWeight.Normal, color = primaryColor)
        }
    }
}
