package com.ux.thecolab.ui.showAlarm

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ux.thecolab.R

@Composable
fun ShowAlarmScreen(
    onClickClose: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        val primaryColor: Color = MaterialTheme.colorScheme.primary
        val whiteColor: Color = MaterialTheme.colorScheme.onSecondary
        val tertiaryColor: Color = MaterialTheme.colorScheme.tertiary
        val onTertiaryColor: Color = MaterialTheme.colorScheme.onTertiary

        Image(
            contentScale = ContentScale.FillHeight,
            painter = painterResource(id = R.drawable.fondo_pagina_principal),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Suministrar Omeprazol", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = primaryColor)

            Spacer(modifier = Modifier.padding(30.dp))
            Image(
                contentScale = ContentScale.FillWidth,
                painter = painterResource(id = R.drawable.medicina),
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 100.dp)
                    .width(160.dp)
                    .height(190.dp)
            )

            Spacer(modifier = Modifier.padding(20.dp))
            Text(text = "A", fontSize = 18.sp, fontWeight = FontWeight.Normal, color = primaryColor)

            Spacer(modifier = Modifier.padding(10.dp))
            Text(text = "Maria Lara", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = primaryColor)

            Spacer(modifier = Modifier.padding(10.dp))
            Text(text = "Omeprazol", fontSize = 18.sp, fontWeight = FontWeight.Normal, color = primaryColor)

            Spacer(modifier = Modifier.padding(15.dp))
            FloatingActionButton(onClick = { onClickClose() } ,
                containerColor = onTertiaryColor,
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier.size(80.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = "Add FAB",
                    tint = whiteColor,
                )
            }
            Text(text = "CONFIRMAR", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = onTertiaryColor)
        }
    }
}