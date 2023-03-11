package com.ux.thecolab.ui

import android.widget.Toast
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

@Composable
fun RegisterScreen(
    onClickRoot: () -> Unit = {},
    showSnackbar: (String, SnackbarDuration) -> Unit
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

            Spacer(modifier = Modifier.padding(15.dp))
            CustomTextField(unfocusedColor = unfocusedColor, focusedColor = focusedColor, primaryColor = primaryColor, text = "Correo electrónico")

            Spacer(modifier = Modifier.padding(10.dp))
            CustomTextField(unfocusedColor = unfocusedColor, focusedColor = focusedColor, primaryColor = primaryColor, text = "Nombre de usuario")

            Spacer(modifier = Modifier.padding(10.dp))
            CustomTextFieldPassword(unfocusedColor = unfocusedColor, focusedColor = focusedColor, primaryColor = primaryColor, text = "Contraseña")

            Spacer(modifier = Modifier.padding(10.dp))
            CustomTextFieldPassword(unfocusedColor = unfocusedColor, focusedColor = focusedColor, primaryColor = primaryColor, text = "Repetir contraseña")

            val snackState = remember { SnackbarHostState() }
            val snackScope = rememberCoroutineScope()
            SnackbarHost(hostState = snackState, Modifier)
            Spacer(modifier = Modifier.padding(15.dp))
            CustomButton(containerColor = primaryColor, contentColor = whiteColor, text = "Crear cuenta", onClick = { showSnackbar("Registro exitoso", SnackbarDuration.Short); onClickRoot()
            })

            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = "O", fontSize = 14.sp, fontWeight = FontWeight.Normal, color = primaryColor)

            Spacer(modifier = Modifier.padding(5.dp))
            CustomButton(containerColor = primaryColor, contentColor = whiteColor, text = "Iniciar sesión", onClick = onClickRoot)
        }
    }
}