package com.jetbrains.studysensores

sealed class Screens(val route: String) {
    data object List: Screens("list")
    data object SensorData: Screens("sensorData/{sensorType}"){
        fun createRoute(sensorType: Int) = "sensorData/$sensorType"
    }
}