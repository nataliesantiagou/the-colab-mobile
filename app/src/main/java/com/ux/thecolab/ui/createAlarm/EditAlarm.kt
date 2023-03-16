package com.ux.thecolab.ui.createAlarm

import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ux.thecolab.components.*
import com.ux.thecolab.R
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
    toggleVisibleTopBar: (Boolean) -> Unit = {}
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
                .fillMaxSize()
                .verticalScroll(enabled = true, state = rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
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
                Row(modifier = Modifier.padding(horizontal = 45.dp)) {
                    DropDownList(
                        options = itemsPacient,
                        value = patient.value,
                        selectedOptionText = { patient.value = it })
                }

                Spacer(modifier = Modifier.padding(15.dp))
                Row(
                    modifier = Modifier.padding(start = 45.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                    ) {
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
                    }

                    val context = LocalContext.current

                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_help_24),
                        contentDescription = "kkk",
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                Toast
                                    .makeText(
                                        context,
                                        "Puedes ingresar más de un medicamento separado por coma",
                                        Toast.LENGTH_LONG
                                    )
                                    .show()
                            },
                        tint = MaterialTheme.colorScheme.onTertiary
                    )
                }

                Spacer(modifier = Modifier.padding(15.dp))
                Row(modifier = Modifier.padding(horizontal = 45.dp)) {
                    DropDownList(value = frecuence.value, selectedOptionText = { frecuence.value = it }, toggleBar= toggleBar)
                }


                Spacer(modifier = Modifier.padding(15.dp))
                Row(modifier = Modifier.padding(horizontal = 45.dp)) {
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
                        modifier = Modifier.fillMaxWidth()
                            .clickable {
                                showDialog = true
                                toggleBar(false)
                            },
                        enabled = false
                    )
                }


                Spacer(modifier = Modifier.padding(15.dp))
                Row(modifier = Modifier.padding(horizontal = 45.dp)) {
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

            }

            if (step.value == 1 || step.value == 3 || step.value == 4) {
                Image(
                    contentScale = ContentScale.FillWidth,
                    painter = painterResource(id = R.drawable.medicina),
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 40.dp)
                        .weight(1f)
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
                    .padding(horizontal = 45.dp),
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
