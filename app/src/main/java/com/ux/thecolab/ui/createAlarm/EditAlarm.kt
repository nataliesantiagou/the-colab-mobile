package com.ux.thecolab.ui.createAlarm

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ux.thecolab.components.*
import com.ux.thecolab.R
import com.ux.thecolab.data.PatientItem
import com.ux.thecolab.data.PatientViewModel
import com.ux.thecolab.data.PatientViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAlarmScreen(
    onClickCreate: () -> Unit = {},
    goBack: () -> Unit = {},
    showSnackbar: (String, SnackbarDuration) -> Unit,
    toggleBar: (Boolean) -> Unit = {},
    toggleVisibleTopBar: (Boolean) -> Unit = {},
    togglelarmCreated: (Boolean) -> Unit = {}
) {
    val primaryColor: Color = MaterialTheme.colorScheme.primary
    val focusedColor: Color = MaterialTheme.colorScheme.onPrimary
    val unfocusedColor: Color = MaterialTheme.colorScheme.secondary
    val whiteColor: Color = MaterialTheme.colorScheme.onSecondary

    val name = remember { mutableStateOf("Omeprazol") }
    val frecuence = remember { mutableStateOf("Cada 3 dias") }
    val patient = remember { mutableStateOf("Maria Lara") }
    val hour = remember { mutableStateOf("06:00 a.m") }
    val contact = remember { mutableStateOf("0000000000") }

    var step = remember { mutableStateOf(0) };

    val context = LocalContext.current
    val mPatientViewModel: PatientViewModel = viewModel(
        factory = PatientViewModelFactory(context.applicationContext as Application)
    )

    val itemsPacient = mPatientViewModel.readAllData.observeAsState(listOf()).value

    var showDialog by remember { mutableStateOf(false) }
    var showFrequency by remember { mutableStateOf(false) }
    var showListDay by remember { mutableStateOf(false) }

    Scaffold(
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(enabled = true, state = rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.padding(15.dp))
            Text(
                text = "Editar Recordatorio",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = primaryColor
            )
            Spacer(modifier = Modifier.padding(15.dp))
            if (step.value == 0) {
                DropDownList(
                    options = itemsPacient,
                    value = patient.value,
                    selectedOptionText = { patient.value = it })

                Spacer(modifier = Modifier.padding(15.dp))
                CustomTextFieldForm(
                    unfocusedColor = unfocusedColor,
                    focusedColor = focusedColor,
                    primaryColor = primaryColor,
                    text = "Nombre del medicamento",
                    value = name.value,
                    onValueChange = {
                        name.value = it
                    },
                )

                Spacer(modifier = Modifier.padding(15.dp))
                DropDownList(value = frecuence.value, selectedOptionText = { frecuence.value = it }, toggleBar= toggleBar)

                Spacer(modifier = Modifier.padding(15.dp))
                OutlinedTextField(
                    value = hour.value,
                    onValueChange = {  },
                    label = { Text("Hora") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = unfocusedColor,
                        unfocusedLabelColor = unfocusedColor,
                        focusedBorderColor = focusedColor,
                        focusedLabelColor = focusedColor,
                        textColor = primaryColor,
                        disabledTextColor = primaryColor,
                        disabledBorderColor = unfocusedColor,
                        disabledLabelColor = unfocusedColor
                    ),
                    readOnly = true,
                    modifier = Modifier.clickable {
                        showDialog = true
                        toggleBar(false)
                    },
                    enabled = false
                )

                Spacer(modifier = Modifier.padding(15.dp))
                CustomTextFieldForm(
                    unfocusedColor = unfocusedColor,
                    focusedColor = focusedColor,
                    primaryColor = primaryColor,
                    text = "Número de contacto",
                    value = contact.value,
                    onValueChange = {
                        contact.value = it
                    },
                )
            }

            // step 2
//            if (step.value == 1) {
//                Text(text = "Foto medicamento")
//                Card(modifier = Modifier
//                    .padding(39.dp, 10.dp)
//                    .border(1.dp, MaterialTheme.colorScheme.primary),
//                    colors = CardDefaults.cardColors(
//                        containerColor = MaterialTheme.colorScheme.background,
//                    ), content = {
//                        Column(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(top = 50.dp, bottom = 70.dp)
//                                .clickable { step.value = 2
//                                    toggleBar(false)
//                                    toggleVisibleTopBar(false)},
//                            horizontalAlignment = Alignment.CenterHorizontally,
//                            verticalArrangement = Arrangement.Center
//                        ) {
//                            Text(text = "Tomar foto")
//                            Spacer(modifier = Modifier.padding(15.dp))
//                            Icon(
//                                painter = painterResource(id = R.drawable.ic_outline_camera_alt_24),
//                                contentDescription = "Localized description",
//                                modifier = Modifier.size(48.dp)
//                            )
//                        }
//                    }
//                )
//
//                Spacer(modifier = Modifier.padding(60.dp))
//            }

            if (step.value == 1 || step.value == 3 || step.value == 4) {
                Image(
                    contentScale = ContentScale.FillWidth,
                    painter = painterResource(id = R.drawable.medicina),
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 40.dp)
                        .clickable { step.value = 2
                                    toggleBar(false)
                                    toggleVisibleTopBar(false)},
                )
            }

            Spacer(modifier = Modifier.padding(15.dp))
            Row(
                modifier = Modifier
                    .background(Color.Transparent)
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CustomButton(
                    containerColor = primaryColor,
                    contentColor = whiteColor,
                    text = "Cancelar",
                    onClick = {
                        if (step.value == 0) {
                            goBack()
                        } else {
                            if (step.value == 3) {
                                step.value -= 1
                                toggleBar(false)
                                toggleVisibleTopBar(false)
                            } else if (step.value == 4) {
                                step.value = 1
                            } else {
                                step.value -= 1
                            }
                        }
                    })
                val btnText = if (step.value == 0) "Siguiente" else if (step.value == 3) "Confirmar" else "Guardar"
                CustomButton(
                    containerColor = unfocusedColor,
                    contentColor = whiteColor,
                    text = btnText,
                    onClick = {
                        if (step.value == 1 || step.value == 4) {
                            showSnackbar("Registro exitoso", SnackbarDuration.Short)
                            onClickCreate()
                            togglelarmCreated(true)
                        } else {
                            step.value += 1
                        }
                    })
            }
            Spacer(modifier = Modifier.padding(15.dp))
        }

        // dialogos
        if (showDialog) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                CustomSpinnerHour() {
                    showDialog = false
                    hour.value = it
                    toggleBar(true)
                }
            }
        }

        if (frecuence.value == "Intervalo de dias") {
            showFrequency = true
        }

        if (showFrequency) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                FrequencyPickerUI() {
                    showFrequency = false
                    frecuence.value = it
                    toggleBar(true)
                }
            }
        }

        if (frecuence.value == "Dias especificos") {
            showListDay = true
        }

        if (showListDay) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                CustomListDays(primaryColor, whiteColor) {
                    showListDay = false
                    frecuence.value = it
                    toggleBar(true)
                }
            }
        }

        if (step.value == 2) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
            ) {
                CustomFullPhoto(onClick = { step.value = 3
                    toggleBar(true)
                    toggleVisibleTopBar(true)
                })
            }
        }
    }
}
