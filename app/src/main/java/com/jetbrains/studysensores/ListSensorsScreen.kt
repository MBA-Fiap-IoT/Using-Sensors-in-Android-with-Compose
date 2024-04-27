package com.jetbrains.studysensores

import android.hardware.Sensor
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ListSensors(
    onNextClicked: (Int) -> Unit,
    sensors: List<Sensor>,
    modifier: Modifier = Modifier) {


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