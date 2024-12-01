package com.jacknic.android.airport.ui.miclock

import android.view.LayoutInflater
import android.view.View
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.catalog.framework.annotations.Sample
import com.jacknic.android.airport.R

/**
 * 使用文件存储信息
 *
 * @author Jacknic
 */

@OptIn(ExperimentalMaterial3Api::class)
@Sample(
    name = "触摸旋转控件",
    description = "模仿小米时钟触摸旋转样式",
    tags = ["view", "clock"]
)
@Composable
fun MiClockScreen() {
    Column {
        AndroidView<View>({
            val inflater = LayoutInflater.from(it)
            inflater.inflate(R.layout.mi_clock_group, null)
        })
    }
}