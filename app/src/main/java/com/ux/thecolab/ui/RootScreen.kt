package com.ux.thecolab.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ux.thecolab.R
import androidx.compose.runtime.saveable.rememberSaveable

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

            Spacer(modifier = Modifier.padding(34.dp))
            Image(
                contentScale = ContentScale.FillHeight,
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(126.dp)
            )

            Spacer(modifier = Modifier.padding(25.dp))
            var name by remember { mutableStateOf("") }
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre de usuario") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = unfocusedColor,
                    unfocusedLabelColor = unfocusedColor,
                    focusedBorderColor = focusedColor,
                    focusedLabelColor = focusedColor,
                    textColor = primaryColor
                )
            )

            Spacer(modifier = Modifier.padding(15.dp))
            Button(
                onClick = onClickRegister,
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryColor,
                    contentColor = whiteColor)
            ) {
                Text("Iniciar sesión")
            }
        }
    }
}
