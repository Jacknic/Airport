package com.jacknic.android.airport.ui.gyroscope

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.catalog.framework.annotations.Sample

@Sample(
    name = "陀螺仪数据",
    description = "使用陀螺仪",
    tags = ["sensor", "gyroscope"],
    documentation = "陀螺仪数据的获取及计算"
)
@Composable
fun GyroscopeScreen() {
    val vm = hiltViewModel<GyroscopeViewModel>()
    val values by vm.valuesGyro.collectAsState()
    val valuesAcc by vm.valuesAcc.collectAsState()
    Column {
        Text("陀螺数据")
        Text("X=${values.getOrNull(0)}")
        Text("Y=${values.getOrNull(1)}")
        Text("Z=${values.getOrNull(2)}")

        HorizontalDivider()

        Text("加速度传感器数据")
        Text("X=${valuesAcc.getOrNull(0)}")
        Text("Y=${valuesAcc.getOrNull(1)}")
        Text("Z=${valuesAcc.getOrNull(2)}")
    }
}