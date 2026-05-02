package com.example.modul3compose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.modul3compose.ui.screen.DetailScreen
import com.example.modul3compose.ui.screen.HomeScreen
import com.example.modul3compose.ui.screen.SettingsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("detail/{songId}") { backStackEntry ->
            val songId = backStackEntry.arguments?.getString("songId")?.toIntOrNull()
            DetailScreen(navController = navController, songId = songId)
        }
        composable("settings") {
            SettingsScreen(navController = navController)
        }
    }
}