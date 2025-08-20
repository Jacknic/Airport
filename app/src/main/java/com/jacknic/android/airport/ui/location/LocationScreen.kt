package com.jacknic.android.airport.ui.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.LocationOn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.google.android.catalog.framework.annotations.Sample

@Sample(
    name = "定位",
    description = "定位参考例子",
    tags = ["location"],
    documentation = "这是一个定位操作的参考例子"
)
@Composable
fun LocationScreen() {
    val context = LocalContext.current
    val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
    var granted by remember { mutableStateOf(false) }
    val updateGranted = {
        granted = permissions.all {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }
    }
    val resultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) {
        updateGranted()
    }

    var location by remember { mutableStateOf<Location?>(null) }
    LaunchedEffect(Unit) {
        updateGranted()
    }
    DisposableEffect(granted) {
        val lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val listener = LocationListener {
            location = it
        }
        if (granted) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10_000L, 1f, listener)
        }

        onDispose {
            if (granted) {
                lm.removeUpdates(listener)
            }
        }
    }


    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            if (granted) {
                Toast.makeText(context, "开始定位", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "申请权限", Toast.LENGTH_SHORT).show()
                resultLauncher.launch(permissions)
            }

        }) {
            Icon(Icons.TwoTone.LocationOn, "")
        }
    }) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            Text("lat: ${location?.latitude}")
            Text("lon: ${location?.longitude}")
            Text("speed: ${location?.speed}")
            Text("location: $location")
        }
    }
}