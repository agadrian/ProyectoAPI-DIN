package com.es.interfazproyapi.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.es.interfazproyapi.model.NavigationItem
import com.es.interfazproyapi.ui.theme.white

@Composable
fun MyBottomAppBar(
    navController: NavHostController,
    selectedIndex: Int,  // Recibir el índice seleccionado
    onItemSelected: (Int) -> Unit,  // Recibir el callback
    listItems: List<NavigationItem>,
    modifier: Modifier = Modifier
) {


//    // Detecta la ruta actual
//    val currentBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = currentBackStackEntry?.destination?.route


    //var index by remember { mutableIntStateOf(0) }
    Column {

        HorizontalDivider(
            color = white,
            thickness = 0.1.dp,
            modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp)
        )

        NavigationBar(
            containerColor = Color.Black
        ) {
            listItems.forEachIndexed { index, item ->
                val isSelected = index == selectedIndex

                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        if (!isSelected) {
                            item.route?.let {
                                navController.navigate(it) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                        onItemSelected(index)
                    },
                    colors = NavigationBarItemDefaults.colors(
                        //TODO
                        indicatorColor = Color.Black,
                        selectedTextColor = Color.White,
                        unselectedTextColor = Color.Gray,
                        selectedIconColor = Color.White,
                        unselectedIconColor = Color.Gray
                    ),

                    icon = {
                        Icon(
                            imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                            contentDescription = item.title
                        )
                    },
                    label = {
                        Text(
                            text = item.title
                        )
                    }
                )
            }
        }
    }
}




        
//        NavigationBarItem(
//            selected = index == 1,
//            onClick = { index = 1 },
//            colors = NavigationBarItemDefaults.colors(
//                //TODO
//                indicatorColor = Color.Black,
//                selectedTextColor = Color.White,
//                unselectedTextColor = Color.Gray,
//                selectedIconColor = Color.White,
//                unselectedIconColor = Color.Gray
//            ),
//            icon = {
//                Icon(
//                    imageVector = Icons.Default.Place,
//                    contentDescription = "Place"
//                )
//            },
//            label = {
//                Text(
//                    text = "Places"
//                )
//            }
//        )



//@Composable
//@Preview
//fun PreviewBottomBar(){
//    MyBottomAppBar(
//        rememberNavController()
//    )
//}