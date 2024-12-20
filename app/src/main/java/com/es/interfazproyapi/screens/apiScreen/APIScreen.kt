package com.es.interfazproyapi.screens.apiScreen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.es.interfazproyapi.utils.customToast

@Composable
fun APIScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
){
    val context = LocalContext.current

    val listaEndPointsGet = listOf(
        "/usuarios", "/usuarios/{id}", "/rutas", "/rutas/{id}"
    )

    val listaEndPointsPost = listOf(
        "/usuarios/register", "/usuarios/login", "/rutas"
    )


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Header("Endpoints")

        Spacer(Modifier.height(40.dp))

        Body(
            listaEndPointsGet,
            listaEndPointsPost,
            context
        )
    }


}

@Composable
fun Header(
    titulo: String
){
    Text(
        text = titulo,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        color = MaterialTheme.colorScheme.onPrimary,
        modifier = Modifier

    )
}


@Composable
fun Body(
    listaEndPointsGet: List<String>,
    listaEndPointsPost: List<String>,
    context: Context
){
    ShowListEndPoints(listaEndPointsGet,"Métodos GET" , context)

    Spacer(Modifier.height(25.dp))

    ShowListEndPoints(listaEndPointsPost,"Métodos POST" , context)
}



@Composable
fun ShowListEndPoints(
    lista: List<String>,
    titulo: String,
    context: Context
){

    Text(
        text = titulo,
        color = MaterialTheme.colorScheme.onPrimary,
        fontWeight = FontWeight.Bold,
        textDecoration = TextDecoration.Underline,
        fontSize = 18.sp
    )

    lista.forEach { endpoint ->
        APIEndPointItem(
            name = endpoint,
            onButtonClick = { customToast(context, "Soon...") }
        )
    }
}


@Composable
fun APIEndPointItem(
    name: String,
    onButtonClick: () -> Unit
){
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = name,
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Button(
            onClick = onButtonClick,
            modifier = Modifier
                .padding(start = 10.dp)
                .height(30.dp)
                .width(70.dp),
            contentPadding = PaddingValues(0.dp)
        ){
            Text(
                text = "Acceder",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

}


@Composable
@Preview
fun PreviewAPIScreen(){
    APIScreen(
        Modifier, rememberNavController(),
    )
}