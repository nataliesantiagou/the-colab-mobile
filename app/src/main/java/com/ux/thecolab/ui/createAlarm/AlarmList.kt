package com.ux.thecolab.ui

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ux.thecolab.components.CustomHeader

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AlbumListScreen(

) {
    val primaryColor: Color = MaterialTheme.colorScheme.primary
    val whiteColor: Color = MaterialTheme.colorScheme.onSecondary
    val tertiaryColor: Color = MaterialTheme.colorScheme.tertiary

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ },
                containerColor = primaryColor,
                shape = RoundedCornerShape(50.dp),
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add FAB",
                    tint = Color.White,
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ){
        LazyColumn(
            modifier = Modifier.fillMaxHeight()
        ) {
            stickyHeader {
                CustomHeader(fontColor = whiteColor, backgroundColor = primaryColor, text = "THE COLAB")
            }

            item {
                Column(
                    modifier = Modifier.fillParentMaxHeight()
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.padding(15.dp))
                    Text(text = "Recordatorios", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = primaryColor)

                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(text = "No tienes recordatorios creados", fontSize = 14.sp, fontWeight = FontWeight.Normal, color = tertiaryColor)
                }
            }
        }
    }


}

