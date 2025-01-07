package com.jacknic.android.airport.ui.view

import android.view.LayoutInflater
import android.view.View
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.catalog.framework.annotations.Sample
import com.jacknic.android.airport.R

/**
 * 约束布局
 *
 * @author Jacknic
 */

@Sample(
    name = "约束布局",
    description = "约束布局参考",
    tags = ["view", "Constrain"]
)
@Composable
fun ConstraintScreen() {
    Column {
        AndroidView<View>({
            val inflater = LayoutInflater.from(it)
            inflater.inflate(R.layout.constraint_view, null)
        })
    }
}