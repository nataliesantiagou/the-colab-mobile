package com.ux.thecolab.ui.detailAlarm

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ux.thecolab.R
import com.ux.thecolab.components.CustomFloatCreate
import com.ux.thecolab.components.CustomFloatEdit

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailAlarmScreen(
    onClickEdit : () -> Unit = {},
) {

    val primaryColor: Color = MaterialTheme.colorScheme.primary
    val whiteColor: Color = MaterialTheme.colorScheme.onSecondary
    val tertiaryColor: Color = MaterialTheme.colorScheme.tertiary
    val onTertiaryColor: Color = MaterialTheme.colorScheme.onTertiary

    Scaffold(
        floatingActionButton = {
            CustomFloatEdit(containerColor = onTertiaryColor, contentColor = whiteColor, onClick = onClickEdit)
        },
        floatingActionButtonPosition = FabPosition.End,
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxHeight()
        ) {

            item {
                Column(
                    modifier = Modifier
                        .fillParentMaxHeight()
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "Detalle recordatorio",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = primaryColor,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.padding(15.dp))

                    Text(
                        text = "Nombre del paciente:",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = primaryColor
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(
                        text = "Maria Lara",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = primaryColor
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    Text(
                        text = "Nombre del medicamento:",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = primaryColor
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Row(
                        modifier = Modifier
                            .background(Color.Transparent)
                            .fillMaxWidth(),
                    ) {
                        Text(
                            text = "Omeprazol",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = primaryColor
                        )

                        Image(
                            contentScale = ContentScale.FillWidth,
                            painter = painterResource(id = R.drawable.medicina),
                            contentDescription = null,
                            modifier = Modifier.padding(horizontal = 10.dp).width(105.dp).height(140.dp)
                        )
                    }

                    Spacer(modifier = Modifier.padding(10.dp))

                    Text(
                        text = "Frecuencia:",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = primaryColor
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(
                        text = "Cada 3 dias",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = primaryColor
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    Text(
                        text = "Hora:",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = primaryColor
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(
                        text = "06:00 a.m.",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = primaryColor
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    Text(
                        text = "Número de contacto:",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = primaryColor
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(
                        text = "3216549870",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = primaryColor
                    )
                }
            }
        }
    }
}