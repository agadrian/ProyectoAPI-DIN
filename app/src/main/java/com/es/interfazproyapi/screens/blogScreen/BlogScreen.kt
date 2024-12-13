package com.es.interfazproyapi.screens.blogScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.es.interfazproyapi.navegation.AppScreen


@Composable
fun BlogScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Column(
        Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Blog Screen",
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

@Preview
@Composable
fun Preview(){
    BlogScreen(Modifier, rememberNavController())
}