package com.ux.thecolab.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSpinnerHour(
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
                .padding(vertical = 10.dp, horizontal = 16.dp)
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

            Spacer(modifier = Modifier.height(20.dp))

            DateSelectionSection(
                onHoursChosen = { chosenHour.value = it },
                onMinutesChosen = { chosenMinutes.value = it },
                onZoneChosen = { chosenZone.value = it },
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
            .height(150.dp)
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
                    if (it == listState.firstVisibleItemIndex + 1) {
                        Divider(color = MaterialTheme.colorScheme.primary, thickness = 1.dp, modifier = Modifier.width(30.dp))
                        Spacer(modifier = Modifier.height(5.dp))
                    }

                    Text(
                        text = items[index],
                        modifier = Modifier.alpha(if (it == listState.firstVisibleItemIndex + 1) 1f else 0.3f),
                        textAlign = TextAlign.Center
                    )

                    if (it == listState.firstVisibleItemIndex + 1) {
                        Spacer(modifier = Modifier.height(5.dp))
                        Divider(color = MaterialTheme.colorScheme.primary, thickness = 1.dp, modifier = Modifier.width(30.dp))
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                })
            }
        )
    }
}

val hours = listOf("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12")
val minutes = listOf("00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55")
val zone = listOf("a.m.", "p.m.")