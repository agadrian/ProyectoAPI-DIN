package com.es.interfazproyapi.screens.mainScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PinDrop
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {

        HeaderMain()

        Spacer(Modifier.height(15.dp))

        BodyMain()

    }
}

@Composable
fun HeaderMain(){
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Text(
            text = "Cards de ejemplo para un futuro",
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun BodyMain(){
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center

    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Fila 1
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OptionCard(
                    title = "Opc 1",
                    description = "Desc 1",
                    icon = Icons.Filled.PinDrop
                    ,
                    onClick = {  }
                )
                OptionCard(
                    title = "Opc 2",
                    description = "Desc 2",
                    icon = Icons.Filled.PinDrop,
                    onClick = {  }
                )
            }

            // Fila 2
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OptionCard(
                    title = "Opc 3",
                    description = "Desc 3",
                    icon = Icons.Filled.PinDrop,
                    onClick = {  }
                )
                OptionCard(
                    title = "Opc 4",
                    description = "Desc 4",
                    icon = Icons.Filled.PinDrop,
                    onClick = {  }
                )
            }

            // Fila 3
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OptionCard(
                    title = "Opc 5",
                    description = "Desc 5",
                    icon = Icons.Filled.PinDrop,
                    onClick = {  }
                )
                OptionCard(
                    title = "Opc 6",
                    description = "Desc 6",
                    icon = Icons.Filled.PinDrop,
                    onClick = {  }
                )
            }
        }
    }
}



@Composable
fun OptionCard(
    title: String,
    description: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        // Sin sombra de momento
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = Modifier
            .size(180.dp)
            .border(0.5.dp, MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(16.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "Icon",
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.Start)
            )
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = description,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}


@Composable
@Preview
fun PreviewMainScreen(){
    MainScreen(Modifier, rememberNavController())
}