package dev.shrishri1108.jetpackweaterroomassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.shrishri1108.jetpackweaterroomassignment.api.weatherService
import dev.shrishri1108.jetpackweaterroomassignment.theme.JetpackWeaterRoomAssignmentTheme
import dev.shrishri1108.jetpackweaterroomassignment.theme.presentation.HomeScreen
import dev.shrishri1108.jetpackweaterroomassignment.theme.presentation.HomeViewModel
import dev.shrishri1108.jetpackweaterroomassignment.viewmodels.AuthViewModel
import dev.shrishri1108.jetpackweaterroomassignment.viewmodels.WeatherViewModel
import dev.shrishri1108.jetpackweaterroomassignment.viewmodels.WeatherViewModelFactory

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackWeaterRoomAssignmentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    showScreens()
                }
            }
        }
    }

    @Composable
    private fun showScreens(
        authViewModel: AuthViewModel = hiltViewModel<AuthViewModel>(),
        homeViewModel: HomeViewModel = hiltViewModel<HomeViewModel>(),
        weatherViewModel: WeatherViewModel = viewModel(
            factory = WeatherViewModelFactory(weatherService)
        )
    ) {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "onboarding") {
            composable("onboarding") { OnboardingScreen(navController, authViewModel) }
            composable("login") { LoginScreen(navController, authViewModel) }
            composable("userlist") { HomeScreen(navController, homeViewModel) }
            composable("userform") { UserFormScreen(navController, homeViewModel) }
            composable("weather") {
                WeatherScreen(
                    navController = navController,
                    viewModel = weatherViewModel,
                    authsViewModel = authViewModel
                )
            }
        }
    }
}

