package com.ux.thecolab.ui.detailAlarm

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ux.thecolab.components.CustomFloatCreate
import com.ux.thecolab.components.CustomFloatEdit

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailAlarmScreen() {

    val primaryColor: Color = MaterialTheme.colorScheme.primary
    val whiteColor: Color = MaterialTheme.colorScheme.onSecondary
    val tertiaryColor: Color = MaterialTheme.colorScheme.tertiary
    val onTertiaryColor: Color = MaterialTheme.colorScheme.onTertiary

    Scaffold(
        floatingActionButton = {
            CustomFloatEdit(containerColor = onTertiaryColor, contentColor = whiteColor, onClick = {

            })
        },
        floatingActionButtonPosition = FabPosition.End,
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxHeight()
        ) {

        }
    }
}