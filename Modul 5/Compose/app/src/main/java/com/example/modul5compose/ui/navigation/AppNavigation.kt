package com.example.modul5compose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.modul5compose.database.MovieDatabase
import com.example.modul5compose.repository.MovieRepository
import com.example.modul5compose.ui.screen.DetailScreen
import com.example.modul5compose.ui.screen.HomeScreen
import com.example.modul5compose.ui.screen.SettingsScreen
import com.example.modul5compose.util.LanguagePreferences
import com.example.modul5compose.viewmodel.MovieViewModel
import com.example.modul5compose.viewmodel.MovieViewModelFactory

@Composable
fun AppNavigation() {
    val context = LocalContext.current
    val navController = rememberNavController()

    val database = MovieDatabase.getDatabase(context)
    val repository = MovieRepository(database.movieDao())
    val prefs = LanguagePreferences(context)

    val viewModel: MovieViewModel = viewModel(
        factory = MovieViewModelFactory(repository, prefs.getUsername())
    )

    NavHost(navController = navController, startDestination = "home") {

        composable("home") {
            HomeScreen(navController = navController, viewModel = viewModel)
        }


        composable("detail/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull()
            DetailScreen(navController = navController, movieId = movieId, viewModel = viewModel)
        }


        composable("settings") {
            SettingsScreen(navController = navController)
        }
    }
}