package com.ux.thecolab.ui

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ux.thecolab.components.CustomButton
import com.ux.thecolab.components.CustomFloatCreate
import com.ux.thecolab.data.PatientViewModel
import com.ux.thecolab.data.PatientViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AlarmListScreen(
    onClickCreate: () -> Unit = {},
    goPatient: () -> Unit = {}
) {
    val primaryColor: Color = MaterialTheme.colorScheme.primary
    val whiteColor: Color = MaterialTheme.colorScheme.onSecondary
    val tertiaryColor: Color = MaterialTheme.colorScheme.tertiary
    val onTertiaryColor: Color = MaterialTheme.colorScheme.onTertiary
    val openDialog = remember { mutableStateOf(false) }

    val context = LocalContext.current
    val mTodoViewModel: PatientViewModel = viewModel(
        factory = PatientViewModelFactory(context.applicationContext as Application)
    )

    val itemsPacient = mTodoViewModel.readAllData.observeAsState(listOf()).value


    Scaffold(
        floatingActionButton = {
            CustomFloatCreate(containerColor = onTertiaryColor, contentColor = whiteColor, onClick = {
                if (itemsPacient.isEmpty()) {
                    openDialog.value = true
                } else {
                    onClickCreate()
                }
            })
        },
        floatingActionButtonPosition = FabPosition.End,
    ){
        LazyColumn(
            modifier = Modifier.fillMaxHeight()
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillParentMaxHeight()
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



    if (openDialog.value) {
        Dialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onDismissRequest.
                openDialog.value = false
            },
            content = {
                Surface(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    shape = MaterialTheme.shapes.large
                ) {
                    Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "No tienes pacientes registrados por favor crea uno antes de crear un ecordatorio",
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        CustomButton(
                            onClick = {
                                openDialog.value = false
                                goPatient()
                            },
                            text = "Aceptar",
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor= MaterialTheme.colorScheme.onSecondary,
                        )
                    }
                }
            }
        )
    }
}


