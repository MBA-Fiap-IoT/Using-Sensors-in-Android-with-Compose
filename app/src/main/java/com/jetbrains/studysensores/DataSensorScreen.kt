package com.jetbrains.studysensores

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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