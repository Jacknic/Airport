package com.jacknic.android.airport.ui.gyroscope

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.catalog.framework.annotations.Sample
import kotlin.math.abs
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
    val valueQueue by vm.valueQueue.collectAsState()
    val valueAvgs by vm.valueAvgs.collectAsState()
    val valueLast by vm.valueLast.collectAsState()
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
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

        if (!valueQueue.isEmpty()) {
            val text = valueQueue.joinToString("\n") {
                "X=%.3f, Y=%.3f, Z=%.3f".format(it[0], it[1], it[2])
            }
            Text(text)

        }
        // 平均值
        Text("平均值: " + valueAvgs.joinToString { "%.3f".format(it) })
        if (valueLast.size == 3 && valueAvgs.size == 3) {
            val diffX = abs(valueLast[0] - valueAvgs[0])
            val diffY = abs(valueLast[1] - valueAvgs[1])
            val diffZ = abs(valueLast[2] - valueAvgs[2])
            val s = sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ)
            Text("方差值: X=%.3f, Y=%.3f, Z=%.3f, S=%.3f".format(diffX, diffY, diffZ, s))
        }
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