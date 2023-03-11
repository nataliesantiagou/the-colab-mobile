package com.ux.thecolab.ui

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ux.thecolab.components.CustomFloatCreate
import com.ux.thecolab.data.PatientViewModel
import com.ux.thecolab.data.PatientViewModelFactory
import androidx.compose.runtime.livedata.observeAsState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PatientsListScreen(
    onClickCreate: () -> Unit = {}
) {

    val context = LocalContext.current
    val mPatientViewModel: PatientViewModel = viewModel(
        factory = PatientViewModelFactory(context.applicationContext as Application)
    )

    val itemsPacient = mPatientViewModel.readAllData.observeAsState(listOf()).value

    val primaryColor: Color = MaterialTheme.colorScheme.primary
    val whiteColor: Color = MaterialTheme.colorScheme.onSecondary
    val tertiaryColor: Color = MaterialTheme.colorScheme.tertiary
    val onTertiaryColor: Color = MaterialTheme.colorScheme.onTertiary

    Scaffold(
        floatingActionButton = {
            CustomFloatCreate(
                containerColor = onTertiaryColor,
                contentColor = whiteColor,
                onClick = onClickCreate
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.onSecondary)
        ) {

            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.padding(15.dp))
                    Text(
                        text = "Pacientes",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = primaryColor
                    )
                }
            }

            if (itemsPacient.isEmpty()) {

                item {
                    Column(
                        modifier = Modifier
                            .fillParentMaxHeight()
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Spacer(modifier = Modifier.padding(10.dp))
                        Text(
                            text = "No tienes pacientes creados",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = tertiaryColor
                        )
                    }
                }
            } else {

                items(itemsPacient) { item ->
                    Card(modifier = Modifier
                        .padding(39.dp, 10.dp)
                        .border(1.dp, MaterialTheme.colorScheme.primary),
                        colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.onSecondary,
                    ), content = {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp)
                        ) {
                            Text(text = item.itemName, fontSize = 16.sp, color = MaterialTheme.colorScheme.tertiary)
                            Spacer(modifier = Modifier.padding(5.dp))
                            Text(text = item.illness, fontSize = 16.sp, color = MaterialTheme.colorScheme.tertiary)
                        }
                    })
                }
            }
        }
    }


}
