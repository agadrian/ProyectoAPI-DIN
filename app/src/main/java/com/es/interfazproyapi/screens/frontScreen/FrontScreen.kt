package com.es.interfazproyapi.screens.frontScreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.es.interfazproyapi.R
import com.es.interfazproyapi.navegation.AppScreen
import com.es.interfazproyapi.ui.theme.white

@Composable
fun FrontScreen(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit
){
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {

            // Logo
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
            )

            Spacer(Modifier.height(20.dp))

            // Titulo
            Text(
                text = "Routes Tracker",
                fontSize = 36.sp,
                color = MaterialTheme.colorScheme.onPrimary // No uso MaterialTheme porque quiero que sea siemore blanco
            )

            Spacer(Modifier.height(300.dp))

            // Boton acceso
            ButtonEffect(
                text = "Acceder",
                onButtonClick,
                Modifier
                    .padding(horizontal = 45.dp)
            )

        }
    }
}


/**
 * Boton que tiene efecto al ser pulsado. Cambiarle el tamaño llamandolo con el Modifier.padding
 */
@Composable
fun ButtonEffect(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val sizeScale by animateFloatAsState(if (isPressed) 0.98f else 1f, label = "Size effect")

    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 34.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .onFocusChanged { isFocused = it.isFocused }
                .focusable()

                // Para gestionar el borde del foco y el onclick al mismo tiempo.
                .onKeyEvent { keyEvent ->
                    if (keyEvent.type == KeyEventType.KeyDown && keyEvent.key == Key.Enter) {
                        onClick() // Delegar acción al botón
                        true
                    } else {
                        false
                    }
                }
                .then(
                    if (isFocused){
                        Modifier
                            .border(2.dp, Color.White, RoundedCornerShape(32.dp))
                    }else{
                        Modifier

                    }
                )
        ){
            Button(
                onClick = onClick,
                enabled = true,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isPressed){
                        colorResource(R.color.greenButtonPressed)
                    }else{
                        colorResource(R.color.green)
                    },
                    contentColor = colorResource(R.color.black)
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp, vertical = 1.dp)
                    .graphicsLayer(
                        scaleX = sizeScale,
                        scaleY = sizeScale
                    ),
                interactionSource = interactionSource
            ) {
                Text(
                    text = text,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }

    }
}

@Composable
@Preview
fun PreviewFrontScreen(){
    FrontScreen(Modifier, {})
}