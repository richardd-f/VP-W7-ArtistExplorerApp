package com.felix.labw7_artistexplorerapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.felix.labw7_artistexplorerapp.ui.screens.albumDetails.AlbumDetails
import com.felix.labw7_artistexplorerapp.ui.screens.homepage.Homepage

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object AlbumDetails : Screen("album_details")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { Homepage(navController) }
        composable(Screen.AlbumDetails.route) { AlbumDetails(navController) }
    }
}