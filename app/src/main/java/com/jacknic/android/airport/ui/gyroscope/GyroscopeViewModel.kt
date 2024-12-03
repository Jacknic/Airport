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

    private val _values = MutableStateFlow<List<Float>>(emptyList())
    val values = _values.asStateFlow()

    init {
        sm.registerListener(this, gyroscope, 1_000_000)
    }

    override fun onSensorChanged(event: SensorEvent) {
        Log.d(TAG, "onSensorChanged: ${event.values.joinToString(",")}")
        _values.value = event.values.toList()
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        Log.d(TAG, "onAccuracyChanged: accuracy=${accuracy}, sensor=$sensor")
    }

    override fun onCleared() {
        sm.unregisterListener(this)
    }
}