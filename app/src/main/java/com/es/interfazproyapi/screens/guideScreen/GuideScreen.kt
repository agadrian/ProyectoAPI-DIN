package com.es.interfazproyapi.screens.guideScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun GuideScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
){
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
            .verticalScroll(rememberScrollState()),


    ) {

        Text(
            text = "Guide Screen",
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 25.sp,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(20.dp))

        Text(
            text = "Esta es una API para gestionar una App de Trackeo de rutas mediante el GPS del dispositivo móvil.\n Para hacer uso, consiste en una primera creación y validación del usuario, para posteriormente tener acceso a los distintos Endpoints permitidos.\n\nEstos Endpoints, estan controlados para que no cualquiera pueda acceder a ellos, mediante comprobaciones, roles y permisos de los usuarios, etc.\n\nEn un futuro, se especificará en esta guía el uso de cada uno de los endpoints, ya que ahora mismo esta en fase de desarrollo y no estan claros. Algunos de ellos que será inguales sera el de hacer registro y login, que basicamente son de tipo POST, y sus rutas son:\n\n/usuarios/register -> Para registrar un nuevo usuario\n\n/usuarios/login -> Para logearse en la App ",
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 15.sp
        )

    }
}

@Composable
@Preview
fun AAA(){
    GuideScreen(
        Modifier, rememberNavController()
    )
}