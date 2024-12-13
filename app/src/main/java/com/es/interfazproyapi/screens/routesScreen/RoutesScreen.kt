package com.es.interfazproyapi.screens.routesScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun RoutesScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
){
    Column(
        Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Routes Screen",
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 25.sp
        )

        Text(
            text = "Próximamente...",
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 25.sp
        )

    }

}