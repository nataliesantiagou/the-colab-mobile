package com.ux.thecolab.ui.createAlarm

import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
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
    val frecuence = remember { mutableStateOf("") }
    val patient = remember { mutableStateOf("") }
    val hour = remember { mutableStateOf("") }
    val contact = remember { mutableStateOf("") }

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
                text = "Crear Recordatorio",
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
                    onValueChange = { name.value = it },
                )

                Spacer(modifier = Modifier.padding(15.dp))
                DropDownList(value = frecuence.value, selectedOptionText = { frecuence.value = it })

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
                    modifier = Modifier.clickable { showDialog = true },
                    enabled = false
                )

                Spacer(modifier = Modifier.padding(15.dp))
                CustomTextFieldForm(
                    unfocusedColor = unfocusedColor,
                    focusedColor = focusedColor,
                    primaryColor = primaryColor,
                    text = "Número de contacto",
                    value = contact.value,
                    onValueChange = { contact.value = it },
                )
            }

            // step 2
            if (step.value == 1) {
                Text(text = "Foto medicamento")
                Card(modifier = Modifier
                    .padding(39.dp, 10.dp)
                    .border(1.dp, MaterialTheme.colorScheme.primary),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.background,
                    ), content = {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp)
                        ) {

                        }
                    }
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
                            step.value -= 1
                        }
                    })
                val btnText = if (step.value == 0) "Siguiente" else "Guardar"
                CustomButton(
                    containerColor = unfocusedColor,
                    contentColor = whiteColor,
                    text = btnText,
                    onClick = {
                        if (step.value == 1) {
                            showSnackbar("Registro exitoso", SnackbarDuration.Short)
                            onClickCreate()
                        } else {
                            step.value += 1
                        }
                    })
            }
            Spacer(modifier = Modifier.padding(15.dp))
        }

        if (showDialog) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                DatePickerUI() {
                    showDialog = false
                    hour.value = it
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
                CheckboxListExample(primaryColor, whiteColor) {
                    showListDay = false
                    frecuence.value = it
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownList(options: List<PatientItem>, value: String, selectedOptionText: (String) -> Unit) {
    val focusedColor: Color = MaterialTheme.colorScheme.onPrimary
    val unfocusedColor: Color = MaterialTheme.colorScheme.secondary
    val primaryColor: Color = MaterialTheme.colorScheme.primary

    var expanded by remember { mutableStateOf(false) }

// We want to react on tap/press on TextField to show menu
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        OutlinedTextField(
            // The `menuAnchor` modifier must be passed to the text field for correctness.
//            modifier = Modifier.menuAnchor(),
            readOnly = true,
            value = value,
            onValueChange = {},
            label = { Text("Nombre del paciente") },
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownList(value: String, selectedOptionText: (String) -> Unit) {
    val focusedColor: Color = MaterialTheme.colorScheme.onPrimary
    val unfocusedColor: Color = MaterialTheme.colorScheme.secondary
    val primaryColor: Color = MaterialTheme.colorScheme.primary

    val options = listOf("Cada día", "Dias especificos", "Intervalo de dias")
    var expanded by remember { mutableStateOf(false) }

// We want to react on tap/press on TextField to show menu
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        OutlinedTextField(
            // The `menuAnchor` modifier must be passed to the text field for correctness.
//            modifier = Modifier.menuAnchor(),
            readOnly = true,
            value = value,
            onValueChange = {},
            label = { Text("Frecuencia") },
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
                    text = { Text(selectionOption) },
                    onClick = {
                        selectedOptionText(selectionOption)
                        expanded = false
                    },
//                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerUI(
    onDismissRequest: (String) -> Unit
) {
    Card(
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 5.dp)
        ) {

            Spacer(modifier = Modifier.height(30.dp))

            var chosenHour = remember { mutableStateOf("") }
            val chosenMinutes = remember { mutableStateOf("") }
            val chosenZone = remember { mutableStateOf("") }

            val primaryColor: Color = MaterialTheme.colorScheme.primary
            val whiteColor: Color = MaterialTheme.colorScheme.onSecondary

            Row(
                modifier = Modifier
                    .background(Color.Transparent)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = { onDismissRequest("") }, colors = IconButtonDefaults.iconButtonColors(
                        contentColor = primaryColor
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "",
                    )
                }

                val context = LocalContext.current
                CustomButton(
                    containerColor = primaryColor, contentColor = whiteColor, text = "Listo",
                    onClick = {
                        onDismissRequest("${chosenHour.value}: ${chosenMinutes.value} ${chosenZone.value}")
                    }
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            DateSelectionSection(
                onHoursChosen = { chosenHour.value = it },
                onMinutesChosen = { chosenMinutes.value = it },
                onZoneChosen = { chosenZone.value = "" },
            )

            Spacer(modifier = Modifier.height(30.dp))

        }
    }
}

@Composable
fun DateSelectionSection(
    onHoursChosen: (String) -> Unit,
    onMinutesChosen: (String) -> Unit,
    onZoneChosen: (String) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {

        InfiniteItemsPicker(
            items = hours,
            firstIndex = Int.MAX_VALUE / 2,
            onItemSelected = onHoursChosen
        )

        InfiniteItemsPicker(
            items = minutes,
            firstIndex = Int.MAX_VALUE / 2,
            onItemSelected = onMinutesChosen
        )

        InfiniteItemsPicker(
            items = zone,
            firstIndex = Int.MAX_VALUE / 2,
            onItemSelected = onZoneChosen
        )
    }
}

@Composable
fun InfiniteItemsPicker(
    modifier: Modifier = Modifier,
    items: List<String>,
    firstIndex: Int,
    onItemSelected: (String) -> Unit,
) {

    val listState = rememberLazyListState(firstIndex)
    val currentValue = remember { mutableStateOf("") }

    LaunchedEffect(key1 = !listState.isScrollInProgress) {
        onItemSelected(currentValue.value)
        listState.animateScrollToItem(index = listState.firstVisibleItemIndex)
    }

    Box(modifier = Modifier.height(150.dp)) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            state = listState,
            content = {
                items(count = Int.MAX_VALUE, itemContent = {
                    val index = it % items.size
                    if (it == listState.firstVisibleItemIndex + 1) {
                        currentValue.value = items[index]
                    }

                    Spacer(modifier = Modifier.height(10.dp))
//                    Divider(color = MaterialTheme.colorScheme.primary, thickness = 1.dp)

                    Text(
                        text = items[index],
                        modifier = Modifier.alpha(if (it == listState.firstVisibleItemIndex + 1) 1f else 0.3f),
                        textAlign = TextAlign.Center
                    )

//                    Divider(color = MaterialTheme.colorScheme.primary, thickness = 1.dp)
                    Spacer(modifier = Modifier.height(10.dp))
                })
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FrequencyPickerUI(
    onDismissRequest: (String) -> Unit
) {
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

    Card(
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 5.dp)
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier
                    .background(Color.Transparent)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = { onDismissRequest("cada ${number.value} ${frecuency.value}") },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "",
                    )
                }

                val context = LocalContext.current
                CustomButton(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onSecondary,
                    text = "Listo",
                    onClick = {
                        onDismissRequest("cada ${number.value} ${frecuency.value}")
                    }
                )
            }

            Spacer(modifier = Modifier.height(30.dp))


            // content

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
                    DropDownListFrequency(
                        options = numbers,
                        value = number.value,
                        selectedOptionText = { number.value = it },
                        label = "Número"
                    )

                    DropDownListFrequency(
                        options = frecuencies,
                        value = frecuency.value,
                        selectedOptionText = { frecuency.value = it },
                        label = "Frecuencia"
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

data class Frecuency(val itemid: Int, val itemName: String)

// selects frecuencia
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownListFrequency(
    options: List<Frecuency>,
    value: String,
    selectedOptionText: (String) -> Unit,
    label: String
) {
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
            label = { Text(label) },
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

@Composable
fun CheckboxListExample(
    primaryColor: Color,
    whiteColor: Color,
    onDismissRequest: (String) -> Unit
) {
    var itemsDays =
        arrayOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sábado", "Domingo")

    val options = itemsDays.map {
        val checked = remember { mutableStateOf(false) }
        Option(
            checked = checked.value,
            onCheckedChange = { checked.value = it },
            label = it,
        )
    }

    LabelledCheckbox(options = options, primaryColor = primaryColor, whiteColor = whiteColor, onDismissRequest = {
        onDismissRequest("Lunes, Jueves")
    })
}

data class Option(
    var checked: Boolean,
    var onCheckedChange: (Boolean) -> Unit = {},
    val label: String
)

// lista checkbox
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LabelledCheckbox(
    options: List<Option>,
    primaryColor: Color,
    whiteColor: Color,
    onDismissRequest: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 5.dp)
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier
                    .background(Color.Transparent)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = { onDismissRequest() }, colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "",
                    )
                }

                val context = LocalContext.current
                CustomButton(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onSecondary,
                    text = "Listo",
                    onClick = { onDismissRequest() }
                )
            }

            Spacer(modifier = Modifier.height(30.dp))


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

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}


val hours = listOf("01", "02", "03", "04")
val minutes = listOf("05", "10", "15", "20")
val zone = listOf("a.m.", "p.m.")