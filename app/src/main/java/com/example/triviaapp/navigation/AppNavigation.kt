package com.example.triviaapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.triviaapp.ui.screens.TriviaViewModel
import com.example.triviaapp.ui.screens.TriviaScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel = viewModel<TriviaViewModel>()
    NavHost(navController = navController, startDestination = AppScreens.TriviaScreen.name) {
        composable(route = AppScreens.TriviaScreen.name) {
            TriviaScreen(viewModel = viewModel)
        }
    }
}