package com.ux.thecolab.ui

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ux.thecolab.components.*
import com.ux.thecolab.data.PatientItem
import com.ux.thecolab.data.PatientViewModel
import com.ux.thecolab.data.PatientViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class, ExperimentalAnimationApi::class
)
@Composable
fun CreatePatientScreen(
    onClickCreate: () -> Unit = {},
    showSnackbar: (String, SnackbarDuration) -> Unit,
) {
    val primaryColor: Color = MaterialTheme.colorScheme.primary
    val focusedColor: Color = MaterialTheme.colorScheme.onPrimary
    val unfocusedColor: Color = MaterialTheme.colorScheme.secondary
    val whiteColor: Color = MaterialTheme.colorScheme.onSecondary

    val context = LocalContext.current
    val mPatientViewModel: PatientViewModel = viewModel(
        factory = PatientViewModelFactory(context.applicationContext as Application)
    )

    val name = remember { mutableStateOf("") }
    val illness = remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            Row(
                modifier = Modifier
                    .background(Color.Transparent)
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CustomButton(containerColor = primaryColor, contentColor = whiteColor, text = "Cancelar")
                CustomButton(containerColor = unfocusedColor, contentColor = whiteColor, text = "Guardar", onClick = {
                    insertPatientInDB(name.value, illness.value, mPatientViewModel)
                    showSnackbar("Registro exitoso", SnackbarDuration.Short)
                    onClickCreate()
                })
            }
        },
        floatingActionButtonPosition = FabPosition.Center
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
                    Text(text = "Crear pacientes", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = primaryColor)

                    Spacer(modifier = Modifier.padding(15.dp))
                    CustomTextFieldForm(
                        unfocusedColor = unfocusedColor,
                        focusedColor = focusedColor,
                        primaryColor = primaryColor,
                        text = "Nombre del paciente",
                        value = name.value,
                        onValueChange = { name.value = it },
                    )

                    Spacer(modifier = Modifier.padding(15.dp))
                    CustomTextFieldForm(
                        unfocusedColor = unfocusedColor,
                        focusedColor = focusedColor,
                        primaryColor = primaryColor,
                        text = "Enfermedad",
                        value = illness.value,
                        onValueChange = { illness.value = it },
                    )
                }
            }
        }
    }
}

private fun insertPatientInDB(name: String, illness: String, mPatientViewModel: PatientViewModel) {
    if (name.isNotEmpty() || illness.isNotEmpty()) {
        val todoItem = PatientItem(
            itemName = name,
            illness = illness
        )

        mPatientViewModel.addTodo(todoItem)
    }
}



