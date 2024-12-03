package com.jacknic.android.airport.ui.gyroscope

import androidx.compose.foundation.layout.Column
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
    val values by vm.values.collectAsState()
    Column {
        Text("X=${values.getOrNull(0)}")
        Text("Y=${values.getOrNull(1)}")
        Text("Z=${values.getOrNull(2)}")
    }
}