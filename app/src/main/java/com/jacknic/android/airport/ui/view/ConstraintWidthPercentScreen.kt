package com.jacknic.android.airport.ui.view

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.math.MathUtils
import com.google.android.catalog.framework.annotations.Sample
import com.jacknic.android.airport.R
import com.jacknic.android.airport.databinding.ConstraintwidthPercentBinding


/**
 * 约束布局
 *
 * @author Jacknic
 */
var parentWidth = 1
var dXLeft = 1f
var dXRight = 1f
const val minPercent = 0.133f

@Sample(
    name = "约束布局百分比",
    description = "约束布局百分比",
    tags = ["view", "Constrain", "percent"]
)
@Composable
fun ConstraintWidthPercentScreen() {
    Column {
        AndroidView<View>({
            val inflater = LayoutInflater.from(it)
            val binding = ConstraintwidthPercentBinding.inflate(inflater, null, false)
            val constraintLayout = binding.constraintLayout
            constraintLayout.post {
                parentWidth = constraintLayout.width
            }

            setupDragListener(binding.dragHandleLeft, R.id.guidelineVertical1, binding)
            setupDragListener(binding.dragHandleRight, R.id.guidelineVertical2, binding)
            binding.root
        }, modifier = Modifier.fillMaxSize())
    }
}

@SuppressLint("ClickableViewAccessibility")
private fun setupDragListener(
    dragHandle: View,
    guidelineId: Int,
    binding: ConstraintwidthPercentBinding
) {
    dragHandle.setOnTouchListener { view: View, motionEvent: MotionEvent ->
        if (motionEvent.pointerCount != 1) {
            return@setOnTouchListener false
        }
        val isLeft = guidelineId == R.id.guidelineVertical1
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> if (isLeft) {
                dXLeft = view.x - motionEvent.rawX
            } else if (guidelineId == R.id.guidelineVertical2) {
                dXRight = view.x - motionEvent.rawX
            }

            MotionEvent.ACTION_MOVE -> {
                val constraintLayout = binding.constraintLayout
                val constraintSet = ConstraintSet()
                val newX: Float = motionEvent.rawX + (if (isLeft) dXLeft else dXRight)
                val minValue = if (isLeft) 0f else 0.33f
                val maxValue = if (isLeft) 0.66f else 1f
                val percent = MathUtils.clamp(newX / parentWidth, minValue, maxValue) // 限制拖拽范围
                constraintSet.clone(constraintLayout)
                constraintSet.setGuidelinePercent(guidelineId, percent)
                if (percent > 0.33f && isLeft) {
                    constraintSet.setGuidelinePercent(R.id.guidelineVertical2, percent + 0.33f)
                }
                if (percent < 0.33f * 2 && !isLeft) {
                    constraintSet.setGuidelinePercent(R.id.guidelineVertical1, percent - 0.33f)
                }
                constraintSet.applyTo(constraintLayout)
            }

            MotionEvent.ACTION_UP -> {
                Log.d("TAG", "setupDragListener: 手指抬起")
            }

            else -> return@setOnTouchListener true
        }
        true
    }
}