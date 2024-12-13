package com.es.interfazproyapi.navegation

sealed class AppScreen(
    val route: String
) {
    object FrontScreen: AppScreen("FrontScreen")
    object LoginScreen : AppScreen("LoginScreen") {
        /**
         * Permite navegar directamente a la ruta especificada
         */
        fun createRoute(
            email: String
        ): String {
            return "LoginScreen"
        }
    }
    object MainScreen : AppScreen("MainScreen")
    object APIScreen : AppScreen("APIScreen")
    object BlogScreen : AppScreen("BlogScreen")
    object GuideScreen : AppScreen("GuideScreen")
    object SettingsScreen : AppScreen("SettingsScreen")
    object TrackerScreen : AppScreen("TrackerScreen")
    object RoutesScreen : AppScreen("RoutesScreen")
    object UserScreen : AppScreen("UserScreen")

}