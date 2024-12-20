package com.es.interfazproyapi.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Api
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Interests
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Route
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Api
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Interests
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Route
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.es.interfazproyapi.R
import com.es.interfazproyapi.model.NavigationItem
import com.es.interfazproyapi.navegation.AppNavigation
import com.es.interfazproyapi.navegation.AppScreen
import com.es.interfazproyapi.screens.mainScreen.MainScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch



@Composable
fun MyAppWithDrawer(
    isDarkMode: Boolean,
    onDarkModeSwitchChange: (Boolean) -> Unit,
    navController: NavHostController,
    modifier: Modifier = Modifier,

){

    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)


    // Lista de items que tendra el menu hamburguesa dentro
    val items = listOf(
        NavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            route = AppScreen.MainScreen.route
        ),

        NavigationItem(
            title = "Tracker",
            selectedIcon = Icons.Filled.Route,
            unselectedIcon = Icons.Outlined.Route,
            route = AppScreen.TrackerScreen.route
        ),

        NavigationItem(
            title = "Routes",
            selectedIcon = Icons.Filled.Map,
            unselectedIcon = Icons.Outlined.Map,
            route = AppScreen.RoutesScreen.route
        ),

        NavigationItem(
            title = "User",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
            route = AppScreen.UserScreen.route
        ),

        NavigationItem(
            title = "API",
            selectedIcon = Icons.Filled.Api,
            unselectedIcon = Icons.Outlined.Api,
            route = AppScreen.APIScreen.route
        ),

        NavigationItem(
            title = "Blog",
            selectedIcon = Icons.Filled.Book,
            unselectedIcon = Icons.Outlined.Book,
            route = AppScreen.BlogScreen.route
        ),

        NavigationItem(
            title = "Guide",
            selectedIcon = Icons.Filled.Interests,
            unselectedIcon = Icons.Outlined.Interests,
            route = AppScreen.GuideScreen.route
        ),


        NavigationItem(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            badgeCount = 1,
            route = AppScreen.SettingsScreen.route
        )
    )



    // Obtener la ruta actual
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry.value?.destination?.route

    val bottomBarItems = items.take(4)
    val drawerItems = items.takeLast(4)

    // Controlar la visibilidad de la barra lateral, para no mostrarla en la pantalla inicial
    val isDrawerEnabled = currentRoute != AppScreen.FrontScreen.route


    if (isDrawerEnabled){ // Controlar si es la pantalla de Welcome o no
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {

                NavigationDrawerContent(
                    items = drawerItems,
                    currentRoute = currentRoute,
                    onItemSelected = {route ->
                        coroutineScope.launch {
                            // Navegacion evitando duplicar pantalla
                            if (currentRoute != route) {
                                drawerState.close()
                                navController.navigate(route) {
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    },
                    isDarkMode = isDarkMode,
                    onDarkModeSwitchChange = onDarkModeSwitchChange
                )
            }
        ) {

            // Mostrar las barras cuando no es la Welcome Screen
            MyScaffold(
                navController = navController,
                drawerState = drawerState,
                coroutineScope = coroutineScope,
                showBars = true,
                modifier = modifier,
                currentRoute = currentRoute,
                onBottomBarItemSelected = { route ->
                    // Navegacion evitando duplicar pantalla
                    if (currentRoute != route) {
                        navController.navigate(route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                bottomBarItems
            )
        }
    }

    else{
        // No moostrar las barras cuando es la WelcomeScreen
        MyScaffold(
            navController = navController,
            drawerState = null, // Drawer null
            coroutineScope = coroutineScope,
            showBars = false, // Sin barras
            modifier = modifier,
            currentRoute = currentRoute,
            onBottomBarItemSelected = {},
            items
        )
    }

}

/**
 * Scaffold, que permite llamarlo y que se muestre o no, las barras tanto superior como inferior
 */
@Composable
fun MyScaffold(
    navController: NavHostController,
    drawerState: DrawerState?,
    coroutineScope: CoroutineScope,
    showBars: Boolean,
    modifier: Modifier = Modifier,
    currentRoute: String?,
    onBottomBarItemSelected: (String) -> Unit,
    items: List<NavigationItem>
) {
    Scaffold(
        topBar = {
            if (showBars) {
                MyTopAppBar(
                    navController = navController
                ) { coroutineScope.launch { drawerState?.open() } }
            }
        },
        bottomBar = {
            if (showBars) {
                MyBottomAppBar(
                    navController,
                    currentRoute = currentRoute,
                    onItemSelected = onBottomBarItemSelected, // Le pasa la ruta de la pantalla actual
                    listItems = items
                )
            }
        },
        content = { paddingValues ->
            Box(
                modifier = modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                AppNavigation(
                    modifier,
                    navController = navController,
                )
            }
        }
    )
}


/**
 * Menu hamburguesa, contenido
 */
@Composable
fun NavigationDrawerContent(
    items: List<NavigationItem>,
    currentRoute: String?,
    onItemSelected: (String) -> Unit,
    isDarkMode: Boolean,
    onDarkModeSwitchChange: (Boolean) -> Unit,

) {

    ModalDrawerSheet {

        Spacer(Modifier.height(16.dp))

        DarkModeSwitch(
            isDarkMode = isDarkMode,
            onDarkModeSwitchChange = onDarkModeSwitchChange,
            // Le da el mismo padding que al resto de las opcs del menu
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )

        Spacer(Modifier.height(16.dp))

        items.forEach { item ->
            NavigationDrawerItem(
                label = { Text(text = item.title) },
                selected = item.route == currentRoute,
                onClick = { onItemSelected(item.route) },
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = if (isDarkMode) Color.Gray else Color.LightGray
                ),
                icon = {
                    Icon(
                        imageVector = if (item.route == currentRoute) {
                            item.selectedIcon
                        }else {
                            item.unselectedIcon
                        },
                        contentDescription = item.title
                    )
                },
                badge = {
                    item.badgeCount?.let {
                        Text(
                            text = item.badgeCount.toString()
                        )
                    }
                },
                modifier = Modifier
                    .padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}


/**
 * Extra añadido. Switch para cambiar entre modo oscuro y claro
 */

@Composable
fun DarkModeSwitch(
    isDarkMode: Boolean,
    onDarkModeSwitchChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxWidth(),

        horizontalAlignment = Alignment.Start
    ){

        Text(
            text = if (isDarkMode) "Modo claro" else "Modo oscuro",
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 12.sp
        )

        Switch(
            checked = isDarkMode,
            onCheckedChange = { onDarkModeSwitchChange(it) },
            Modifier
                .align(Alignment.Start)
                .scale(0.8f),
            colors = SwitchDefaults.colors(
                checkedThumbColor = colorResource(R.color.white),
                uncheckedThumbColor = colorResource(R.color.gray),
                checkedTrackColor = colorResource(R.color.green),
                uncheckedTrackColor = colorResource(R.color.gray2),
            )
        )
    }
}

@Preview
@Composable
fun PreviewSca(

){
}

