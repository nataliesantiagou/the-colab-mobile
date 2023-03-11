package com.ux.thecolab.ui.createAlarm

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ux.thecolab.components.CustomButton
import com.ux.thecolab.components.CustomTextFieldForm
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ux.thecolab.data.PatientItem
import com.ux.thecolab.data.PatientViewModel
import com.ux.thecolab.data.PatientViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAlarmScreen(
    onClickCreate: () -> Unit = {},
    goBack: () -> Unit = {},
    showSnackbar: (String, SnackbarDuration) -> Unit,
) {
    val primaryColor: Color = MaterialTheme.colorScheme.primary
    val focusedColor: Color = MaterialTheme.colorScheme.onPrimary
    val unfocusedColor: Color = MaterialTheme.colorScheme.secondary
    val whiteColor: Color = MaterialTheme.colorScheme.onSecondary

    val name = remember { mutableStateOf("") }
    val illness = remember { mutableStateOf("") }

    var step =  remember { mutableStateOf(0) };

    val context = LocalContext.current
    val mPatientViewModel: PatientViewModel = viewModel(
        factory = PatientViewModelFactory(context.applicationContext as Application)
    )

    val itemsPacient = mPatientViewModel.readAllData.observeAsState(listOf()).value

    Scaffold(
        floatingActionButton = {
            Row(
                modifier = Modifier
                    .background(Color.Transparent)
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CustomButton(containerColor = primaryColor, contentColor = whiteColor, text = "Cancelar", onClick = {
                    if (step.value == 0){
                        goBack()
                    } else {
                        step.value -= 1
                    }
                })
                val btnText = if (step.value == 0) "Siguiente" else "Guardar"
                CustomButton(containerColor = unfocusedColor, contentColor = whiteColor, text = btnText, onClick = {
                    if (step.value == 1) {
                        showSnackbar("Registro exitoso", SnackbarDuration.Short)
                        onClickCreate()
                    } else{
                        step.value += 1
                    }
                })
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(15.dp))
            Text(text = "Crear Recordatorio", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = primaryColor)

            Spacer(modifier = Modifier.padding(15.dp))
            DropDownList(itemsPacient)

            Spacer(modifier = Modifier.padding(15.dp))
            CustomTextFieldForm(
                unfocusedColor = unfocusedColor,
                focusedColor = focusedColor,
                primaryColor = primaryColor,
                text = "Nombre del medicamento",
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownList (options: List<PatientItem>) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("") }

// We want to react on tap/press on TextField to show menu
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        TextField(
            // The `menuAnchor` modifier must be passed to the text field for correctness.
//            modifier = Modifier.menuAnchor(),
            readOnly = true,
            value = selectedOptionText,
            onValueChange = {},
            label = { Text("Label") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption.itemName) },
                    onClick = {
                        selectedOptionText = selectionOption.itemName
                        expanded = false
                    },
//                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}