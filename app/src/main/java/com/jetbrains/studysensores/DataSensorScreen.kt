package com.jetbrains.studysensores

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

private lateinit var sensorManager: SensorManager


@Composable
fun DataSensor(
    sensorType: Int,
    modifier: Modifier = Modifier) {
    var values0 by remember { mutableFloatStateOf(0.0f) }
    var values1 by remember { mutableFloatStateOf(0.0f) }
    var values2 by remember { mutableFloatStateOf(0.0f) }

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

    sensorManager = LocalContext.current.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    sensorManager.registerListener(
        sensorEventListener,
        sensorManager.getDefaultSensor(sensorType),
        SensorManager.SENSOR_DELAY_NORMAL)

    Column {
        Text(
            text = "[0]: $values0",
            modifier = modifier
        )
        Text(
            text = "[1]: $values1",
            modifier = modifier
        )
        Text(
            text = "[2]: $values2",
            modifier = modifier
        )
    }
}