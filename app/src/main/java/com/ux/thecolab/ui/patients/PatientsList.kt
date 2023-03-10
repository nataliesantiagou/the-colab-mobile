package com.ux.thecolab.ui

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ux.thecolab.components.CustomFloatCreate
import com.ux.thecolab.components.CustomHeader
import com.ux.thecolab.data.PatientViewModel
import com.ux.thecolab.data.PatientViewModelFactory
import androidx.compose.runtime.livedata.observeAsState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PatientsListScreen(
    onClickCreate: () -> Unit = {}
) {

    val context = LocalContext.current
    val mTodoViewModel: PatientViewModel = viewModel(
        factory = PatientViewModelFactory(context.applicationContext as Application)
    )

    val itemsPacient = mTodoViewModel.readAllData.observeAsState(listOf()).value

    val primaryColor: Color = MaterialTheme.colorScheme.primary
    val whiteColor: Color = MaterialTheme.colorScheme.onSecondary
    val tertiaryColor: Color = MaterialTheme.colorScheme.tertiary
    val onTertiaryColor: Color = MaterialTheme.colorScheme.onTertiary

    Scaffold(
        floatingActionButton = {
            CustomFloatCreate(containerColor = onTertiaryColor, contentColor = whiteColor, onClick = onClickCreate)
        },
        floatingActionButtonPosition = FabPosition.End
    ){
        LazyColumn(
            modifier = Modifier.fillMaxHeight()
        ) {
            stickyHeader {
                CustomHeader(fontColor = whiteColor, backgroundColor = primaryColor, text = "THE COLAB")
            }
            if (itemsPacient.isEmpty())
            {

                item {
                    Column(
                        modifier = Modifier
                            .fillParentMaxHeight()
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.padding(15.dp))
                        Text(text = "Pacientes", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = primaryColor)

                        Spacer(modifier = Modifier.padding(10.dp))
                        Text(text = "No tienes pacientes creados", fontSize = 14.sp, fontWeight = FontWeight.Normal, color = tertiaryColor)
                    }
                }
            } else {
                items(itemsPacient) {
                        item -> Text(text = item.itemName)
                }
            }
        }
    }


}

