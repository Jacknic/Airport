package com.jacknic.android.airport.ui.gyroscope

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.catalog.framework.annotations.Sample
import kotlin.math.sqrt


@Sample(
    name = "陀螺仪数据",
    description = "使用陀螺仪",
    tags = ["sensor", "gyroscope"],
    documentation = "陀螺仪数据的获取及计算"
)
@Composable
fun GyroscopeScreen() {
    val vm = hiltViewModel<GyroscopeViewModel>()
    val valuesGyro by vm.valuesGyro.collectAsState()
    val valuesAcc by vm.valuesAcc.collectAsState()
    Column {
        if (valuesGyro.isNotEmpty()) {
            Text("陀螺仪数据")
            val x = valuesGyro[0]
            val y = valuesGyro[1]
            val z = valuesGyro[2]
            Text("X=$x")
            Text("Y=$y")
            Text("Z=$z")
        }
        HorizontalDivider(modifier = Modifier.height(10.dp))

        if (valuesAcc.isNotEmpty()) {
            Text("加速度传感器数据")
            val x = valuesAcc[0]
            val y = valuesAcc[1]
            val z = valuesAcc[2]
            val magnitude = sqrt(x * x + y * y + z * z)
            Text("X=$x")
            Text("Y=$y")
            Text("Z=$z")
            Text("加速度=$magnitude")
        }
    }
}