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

@Composable
fun DataSensor(
    values0: Float,
    values1: Float,
    values2: Float,
    modifier: Modifier = Modifier) {


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