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

    init {
        sm.registerListener(this, gyroscope, 1_000_000)
        sm.registerListener(this, accelerometer, 1_000_000)
    }

    override fun onSensorChanged(event: SensorEvent) {
        Log.d(TAG, "onSensorChanged: ${event.sensor.name} ${event.values.joinToString(",")}")
        val type = event.sensor.type
        if (type == Sensor.TYPE_GYROSCOPE) {
            _valuesGyro.value = event.values.toList()
        } else if (type == Sensor.TYPE_ACCELEROMETER) {
            _valuesAcc.value = event.values.toList()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        Log.d(TAG, "onAccuracyChanged: accuracy=${accuracy}, sensor=$sensor")
    }

    override fun onCleared() {
        sm.unregisterListener(this)
    }
}