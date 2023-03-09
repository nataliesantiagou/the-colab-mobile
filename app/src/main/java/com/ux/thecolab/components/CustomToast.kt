package com.ux.thecolab.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun CustomToast () {
    val context = LocalContext.current
    Toast.makeText(
        context,
        "Registro exitoso.",
        Toast.LENGTH_LONG
    ).show()
}
