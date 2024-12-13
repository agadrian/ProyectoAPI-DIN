package com.es.interfazproyapi.model


import androidx.compose.ui.graphics.vector.ImageVector
import com.es.interfazproyapi.navegation.AppScreen

data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    var badgeCount: Int? = null,
    val route: String? = null
)