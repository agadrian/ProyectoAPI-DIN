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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.es.interfazproyapi.R

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
                color = MaterialTheme.colorScheme.onPrimary
            )

            Spacer(Modifier.height(5.dp))

            HorizontalDivider(
                Modifier
                    .padding(horizontal = 50.dp),
                2.dp,
                MaterialTheme.colorScheme.onPrimary
            )

            Spacer(Modifier.height(100.dp))

            RowCards()

            Spacer(Modifier.height(100.dp))


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

@Composable
fun RowCards(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        FeatureCard(
            icon = Icons.Default.LocationOn,
            title = "Rastrear Ubicación",
            description = "Sigue tu ubicació."
        )
        FeatureCard(
            icon = Icons.Default.Directions,
            title = "Historial de Rutas",
            description = "Accede a tus rutas previas."
        )
        FeatureCard(
            icon = Icons.Default.Analytics,
            title = "Análisis",
            description = "Sigue un análisis semanal."
        )
    }
}


@Composable
fun FeatureCard(
    icon: ImageVector,
    title: String,
    description: String
) {

    Card(
        modifier = Modifier
            .width(120.dp)
            .height(180.dp)
            .padding(8.dp)
            .border(0.2.dp, MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(16.dp))
        ,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        //elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = description,
                fontSize = 12.sp,
                textAlign = TextAlign.Center
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