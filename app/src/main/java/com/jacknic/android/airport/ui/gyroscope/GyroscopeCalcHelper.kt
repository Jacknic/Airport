package com.jacknic.android.airport.ui.gyroscope

import java.util.LinkedList
import kotlin.math.abs

/**
 * 数值偏差计算工具类
 *
 * @author Jack
 */
class GyroscopeCalcHelper {

    private val maxSize = 8
    private val divUnit = 1 / maxSize
    private val valueQueue = LinkedList<Float>()
    private var sumValue = 0f

    /**
     * 放入新数值并计算差值
     */
    fun push(value: Float): Float {
        if (valueQueue.size >= maxSize) {
            // 最后退栈比计算差值
            sumValue += (value - valueQueue.pop())
            valueQueue.push(value)
            return abs(value - sumValue * divUnit)
        } else {
            valueQueue.push(value)
            sumValue += value
            return 0f
        }
    }
}