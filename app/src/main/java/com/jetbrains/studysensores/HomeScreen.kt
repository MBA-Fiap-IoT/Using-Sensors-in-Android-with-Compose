package com.jetbrains.studysensores

import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenAppBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
    )
}

@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = {
            HomeScreenAppBar()
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screens.List.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Screens.List.route) {
                ListSensors(onNextClicked = {
                    navController.navigate(
                        route = Screens.SensorData.createRoute(it)
                    )
                })
            }
            composable(route = Screens.SensorData.route) { backStackEntry ->
                val type = backStackEntry.arguments?.getString("sensorType") ?: "${Sensor.TYPE_ALL}"
                DataSensor(sensorType = type.toInt())
            }
        }
    }
}
