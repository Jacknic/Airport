package com.jacknic.android.airport.ui.gyroscope

import android.app.Application
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.LinkedList

/**
 * 陀螺仪页面数据
 *
 * @author Jacknic
 */
class GyroscopeViewModel(app: Application) : AndroidViewModel(app), SensorEventListener {
    companion object {
        private const val TAG = "GyroscopeViewModel"
    }

    private val sm = app.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val gyroscope = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    private val accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    private val _valuesGyro = MutableStateFlow<List<Float>>(emptyList())
    val valuesGyro = _valuesGyro.asStateFlow()
    private val _valuesAcc = MutableStateFlow<List<Float>>(emptyList())
    val valuesAcc = _valuesAcc.asStateFlow()
    private val maxSize = 10
    private val _valueQueue = MutableStateFlow(LinkedList<List<Float>>())
    val valueQueue = _valueQueue.asStateFlow()
    val valueAvgs = MutableStateFlow(emptyList<Double>())
    val valueLast = MutableStateFlow(emptyList<Float>())

    init {
        sm.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_UI)
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI)
    }

    override fun onSensorChanged(event: SensorEvent) {
        Log.d(TAG, "onSensorChanged: ${event.sensor.name} ${event.values.joinToString(",")}")
        val type = event.sensor.type
        val newList = event.values.toList()
        if (type == Sensor.TYPE_GYROSCOPE) {
            _valuesGyro.value = newList
            _valueQueue.update {
                if (it.size >= maxSize) {
                    val sumX = it.sumOf { l -> l[0].toDouble() } / maxSize
                    val sumY = it.sumOf { l -> l[1].toDouble() } / maxSize
                    val sumZ = it.sumOf { l -> l[2].toDouble() } / maxSize
                    valueAvgs.value = listOf(sumX, sumY, sumZ)
                    it.pop()
                }
                valueLast.value = newList
                it.add(newList)
                it
            }
        } else if (type == Sensor.TYPE_ACCELEROMETER) {
            _valuesAcc.value = newList
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        Log.d(TAG, "onAccuracyChanged: accuracy=${accuracy}, sensor=$sensor")
    }

    override fun onCleared() {
        sm.unregisterListener(this)
    }
}