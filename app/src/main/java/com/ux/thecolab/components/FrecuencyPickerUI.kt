package com.ux.thecolab.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
                        .fillMaxWidth(),
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
        modifier = Modifier.width(165.dp)
    ) {
        OutlinedTextField(
            // The `menuAnchor` modifier must be passed to the text field for correctness.
//            modifier = Modifier.menuAnchor(),
            readOnly = true,
            value = value,
            onValueChange = {},
            label = { Text(label,  fontSize = 14.sp) },
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