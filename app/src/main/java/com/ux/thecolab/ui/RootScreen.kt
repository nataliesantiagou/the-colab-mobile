package com.ux.thecolab.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight(),
        contentAlignment = Alignment.TopCenter
    ) {
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
            Text(text = "THE COLAB", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.primary)

            Spacer(modifier = Modifier.padding(34.dp))
            Image(
                contentScale = ContentScale.FillHeight,
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(126.dp)
            )

            Spacer(modifier = Modifier.padding(26.dp))
            val focusedColor: Color = MaterialTheme.colorScheme.onPrimary
            val unfocusedColor: Color = MaterialTheme.colorScheme.secondary
            OutlinedTextField(
                value = "",
                onValueChange = {  },
                label = { Text("Nombre de usuario") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = unfocusedColor,
                    unfocusedLabelColor = unfocusedColor,
                    focusedBorderColor = focusedColor,
                    focusedLabelColor = focusedColor
                )
            )

        }
    }
}
