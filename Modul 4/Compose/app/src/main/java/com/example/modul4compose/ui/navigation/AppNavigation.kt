package com.example.modul4compose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.modul4compose.ui.screen.DetailScreen
import com.example.modul4compose.ui.screen.HomeScreen
import com.example.modul4compose.ui.screen.SettingsScreen
import com.example.modul4compose.viewmodel.SongViewModel
import com.example.modul4compose.viewmodel.SongViewModelFactory

@Composable
fun AppNavigation() {
    val navController = rememberNavController()


    val viewModel: SongViewModel = viewModel(
        factory = SongViewModelFactory("Yuuukosoo")
    )

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {

            HomeScreen(navController = navController, viewModel = viewModel)
        }
        composable("detail/{songId}") { backStackEntry ->
            val songId = backStackEntry.arguments?.getString("songId")?.toIntOrNull()

            DetailScreen(navController = navController, songId = songId, viewModel = viewModel)
        }
        composable("settings") {
            SettingsScreen(navController = navController)
        }
    }
}