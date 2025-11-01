package com.felix.labw7_artistexplorerapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.felix.labw7_artistexplorerapp.ui.screens.albumDetails.AlbumDetails
import com.felix.labw7_artistexplorerapp.ui.screens.homepage.Homepage
import com.felix.labw7_artistexplorerapp.ui.viewmodel.MainViewModel

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object AlbumDetails : Screen("album_details")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        // Home
        composable(Screen.Home.route) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(Screen.Home.route)
            }
            val viewModel: MainViewModel = hiltViewModel(parentEntry)
            Homepage(navController, viewModel)
        }

        // Album Details
        composable(Screen.AlbumDetails.route) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(Screen.Home.route)
            }
            val viewModel: MainViewModel = hiltViewModel(parentEntry)
            AlbumDetails(navController, viewModel)
        }
    }
}