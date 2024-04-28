package com.jetbrains.studysensores

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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

private lateinit var sensorManager: SensorManager

@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController()
) {
    sensorManager = LocalContext.current.getSystemService(Context.SENSOR_SERVICE) as SensorManager

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
                val sensors = sensorManager.getSensorList(Sensor.TYPE_ALL)
                ListSensors(
                    sensors = sensors,
                    onNextClicked = {sensorType ->
                        navController.navigate(
                            route = Screens.SensorData.createRoute(sensorType)
                        )
                })
            }
            composable(route = Screens.SensorData.route) {backStackEntry ->
                val sensorType = backStackEntry.arguments?.getString("sensorType") ?: "${Sensor.TYPE_ALL}"
                var values0 by rememberSaveable { mutableFloatStateOf(0.0f) }
                var values1 by rememberSaveable { mutableFloatStateOf(0.0f) }
                var values2 by rememberSaveable { mutableFloatStateOf(0.0f) }
                val sensorEventListener = object : SensorEventListener {
                    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {

                    }

                    override fun onSensorChanged(event: SensorEvent) {
                        values0 = event.values[0]

                        if (event.values.size > 1)
                            values1 = event.values[1]

                        if (event.values.size > 2)
                            values2 = event.values[2]
                    }
                }
                remember {
                    sensorManager.unregisterListener(sensorEventListener)
                    sensorManager.registerListener(
                        sensorEventListener,
                        sensorManager.getDefaultSensor(sensorType.toInt()),
                        SensorManager.SENSOR_DELAY_NORMAL
                    )
                }
                DataSensor(
                    values0 = values0,
                    values1 = values1,
                    values2 = values2)
            }
        }
    }
}
