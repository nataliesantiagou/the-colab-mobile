package com.ux.thecolab.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomListDays(
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
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 16.dp)
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
                            .padding(start = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
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