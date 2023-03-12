package com.ux.thecolab.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ux.thecolab.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomFullPhoto(
    onClick: (Boolean) -> Unit
) {
    Card(
        shape = RoundedCornerShape(0.dp),
        modifier = Modifier.padding(start = 40.dp, top = 40.dp, end = 40.dp, bottom = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ), content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    contentScale = ContentScale.FillWidth,
                    painter = painterResource(id = R.drawable.medicina),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.padding(10.dp))

                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_mode_standby_24),
                    contentDescription = "Localized description",
                    modifier = Modifier.size(55.dp).clickable { onClick(true) }
                )
            }
        }
    )
}