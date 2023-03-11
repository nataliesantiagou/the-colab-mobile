package com.ux.thecolab.ui

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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

    val numbers = listOf(
        Frecuency(1, "1"),
        Frecuency(2, "2"),
        Frecuency(3, "3"),
        Frecuency(4, "4"),
    )
    val number = remember { mutableStateOf("") }

    val frecuencies = listOf(
        Frecuency(1, "Dias"),
        Frecuency(2, "Semanas"),
        Frecuency(3, "Meses"),
    )
    val frecuency = remember { mutableStateOf("") }

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

                    CheckboxListExample(primaryColor, whiteColor)

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .background(Color.Transparent)
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            DropDownList(
                                options = numbers,
                                value = number.value,
                                selectedOptionText = { number.value = it })

                            DropDownList(
                                options = frecuencies,
                                value = frecuency.value,
                                selectedOptionText = { frecuency.value = it })
                        }
                    }
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
// lista checkbox
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LabelledCheckbox(
    options: List<Option>,
    primaryColor: Color,
    whiteColor: Color
) {
    Column() {
        options.forEach { option ->
            Row(
                modifier = Modifier
                    .background(Color.Transparent)
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = option.label,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 14.sp,
                    color = primaryColor,
                    fontWeight = FontWeight.Normal
                )

                Checkbox(
                    checked = option.checked,
                    onCheckedChange = option.onCheckedChange,
                    colors = CheckboxDefaults.colors(
                        checkedColor = primaryColor,
                        checkmarkColor = whiteColor
                    )
                )
            }
        }
    }
}


@Composable
fun CheckboxListExample(primaryColor: Color, whiteColor: Color) {
    var itemsDays = arrayOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sábado", "Domingo")

    val options = itemsDays.map {
        val checked = remember { mutableStateOf(false) }
        Option(
            checked = checked.value,
            onCheckedChange = { checked.value = it },
            label = it,
        )
    }

    LabelledCheckbox(options = options, primaryColor = primaryColor, whiteColor = whiteColor)
}

data class Option(
    var checked: Boolean,
    var onCheckedChange: (Boolean) -> Unit = {},
    val label: String
)

// selects frecuencia
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownList (options: List<Frecuency>, value: String, selectedOptionText: (String) -> Unit) {
    val focusedColor: Color = MaterialTheme.colorScheme.onPrimary
    val unfocusedColor: Color = MaterialTheme.colorScheme.secondary
    val primaryColor: Color = MaterialTheme.colorScheme.primary

    var expanded by remember { mutableStateOf(false) }

// We want to react on tap/press on TextField to show menu
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.width(150.dp)
    ) {
        OutlinedTextField(
            // The `menuAnchor` modifier must be passed to the text field for correctness.
//            modifier = Modifier.menuAnchor(),
            readOnly = true,
            value = value,
            onValueChange = {},
            label = { Text("Número") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = unfocusedColor,
                unfocusedLabelColor = unfocusedColor,
                focusedBorderColor = focusedColor,
                focusedLabelColor = focusedColor,
                textColor = primaryColor
            )
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption.itemName) },
                    onClick = {
                        selectedOptionText(selectionOption.itemName)
                        expanded = false
                    },
//                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}

data class Frecuency(val itemid: Int, val itemName: String)
