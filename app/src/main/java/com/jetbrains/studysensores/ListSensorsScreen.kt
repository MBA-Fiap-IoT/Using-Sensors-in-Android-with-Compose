package com.jetbrains.studysensores

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

private lateinit var sensorManager: SensorManager

@Composable
fun ListSensors(
    onNextClicked: (Int) -> Unit,
    modifier: Modifier = Modifier) {
    sensorManager = LocalContext.current.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    var sensors by remember { mutableStateOf(sensorManager.getSensorList(Sensor.TYPE_ALL)) }

    LazyColumn (
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(all = 12.dp)
    ) {
        items(sensors.size) { index ->
            TextButton(onClick = {
                onNextClicked.invoke(sensors[index].type)
            }) {
                Text(
                    text = "${sensors[index].name}",
                    modifier = modifier
                )
            }
        }
    }
}