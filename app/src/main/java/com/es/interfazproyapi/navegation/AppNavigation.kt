package com.es.interfazproyapi.navegation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.es.interfazproyapi.screens.MyAppWithDrawer
import com.es.interfazproyapi.screens.apiScreen.APIScreen
import com.es.interfazproyapi.screens.blogScreen.BlogScreen
import com.es.interfazproyapi.screens.frontScreen.FrontScreen
import com.es.interfazproyapi.screens.guideScreen.GuideScreen
import com.es.interfazproyapi.screens.mainScreen.MainScreen
import com.es.interfazproyapi.screens.routesScreen.RoutesScreen
import com.es.interfazproyapi.screens.settingsScreen.SettingsScreen
import com.es.interfazproyapi.screens.trackerScreen.TrackerScreen
import com.es.interfazproyapi.screens.userScreen.UserScreen


@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
){
    //val navControlador = rememberNavController()



    NavHost(
        navController = navController,
        startDestination = AppScreen.FrontScreen.route
    ) {

        // Ruta especifica de FrontScreen
        composable(
            route = AppScreen.FrontScreen.route

        ) {
            FrontScreen(
                modifier = modifier,
                onButtonClick = {
                    navController.navigate(AppScreen.MainScreen.route)
                }
            )
        }


        // Ruta especifica de LoginScreen TODO: (no me ha dado tiempo))
        composable (
            route = AppScreen.LoginScreen.route,
        ) {
            //TODO

        }


        // Ruta especifica de MainScreen
        composable(
            route = AppScreen.MainScreen.route
        ){
            MainScreen(
                modifier = modifier,
                navController
            )

        }


        // Ruta especifica de APIScreen
        composable(
            route = AppScreen.APIScreen.route
        ){
            APIScreen(
                modifier,
                navController
            )

        }


        // Ruta especifica de BlogScreen
        composable(
            route = AppScreen.BlogScreen.route
        ){
            BlogScreen(
                modifier,
                navController
            )

        }


        // Ruta especifica de GuideScreen
        composable(
            route = AppScreen.GuideScreen.route
        ){
            GuideScreen(
                modifier,
                navController
            )

        }


        // Ruta especifica de SettingsScreen
        composable(
            route = AppScreen.SettingsScreen.route
        ){
            SettingsScreen(
                modifier,
                navController
            )

        }

        // Ruta especifica de TrackerScreen
        composable(
            route = AppScreen.TrackerScreen.route
        ){
            TrackerScreen(
                modifier,
                navController
            )

        }


        // Ruta especifica de RoutesScreen
        composable(
            route = AppScreen.RoutesScreen.route
        ){
            RoutesScreen(
                modifier,
                navController
            )

        }


        // Ruta especifica de UserScreen
        composable(
            route = AppScreen.UserScreen.route
        ){
            UserScreen(
                modifier,
                navController
            )

        }

    }
}
