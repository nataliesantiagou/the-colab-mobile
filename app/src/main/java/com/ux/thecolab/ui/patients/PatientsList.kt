package com.ux.thecolab.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ux.thecolab.components.ColabBottomNavigation
import com.ux.thecolab.components.CustomFloatCreate
import com.ux.thecolab.components.CustomHeader

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PatientsListScreen(
    onClickCreate: () -> Unit = {}
) {
    val primaryColor: Color = MaterialTheme.colorScheme.primary
    val whiteColor: Color = MaterialTheme.colorScheme.onSecondary
    val tertiaryColor: Color = MaterialTheme.colorScheme.tertiary
    val onTertiaryColor: Color = MaterialTheme.colorScheme.onTertiary

    Scaffold(
        floatingActionButton = {
            CustomFloatCreate(containerColor = onTertiaryColor, contentColor = whiteColor, onClick = onClickCreate)
        },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = { ColabBottomNavigation() }
    ){
        LazyColumn(
            modifier = Modifier.fillMaxHeight()
        ) {
            stickyHeader {
                CustomHeader(fontColor = whiteColor, backgroundColor = primaryColor, text = "THE COLAB")
            }

            item {
                Column(
                    modifier = Modifier
                        .fillParentMaxHeight()
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.padding(15.dp))
                    Text(text = "Pacientes", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = primaryColor)

                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(text = "No tienes pacientes creados", fontSize = 14.sp, fontWeight = FontWeight.Normal, color = tertiaryColor)
                }
            }
        }
    }


}

