package com.ryan.agriaid.data.remote.location

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

@SuppressLint("MissingPermission")
fun getCurrentLocation(context: Context, onLocationResult: (Double, Double) -> Unit) {
    val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    fusedLocationClient.lastLocation
        .addOnSuccessListener { location ->
            if (location != null) {
                onLocationResult(location.latitude, location.longitude)
            } else {
                val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 10000).apply {
                    setMinUpdateIntervalMillis(5000)
                    setWaitForAccurateLocation(false)
                }.build()

                val locationCallback = object : LocationCallback() {
                    override fun onLocationResult(locationResult: LocationResult) {
                        for (location in locationResult.locations) {
                            onLocationResult(location.latitude, location.longitude)
                            fusedLocationClient.removeLocationUpdates(this)
                        }
                    }
                }

                fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
            }
        }
        .addOnFailureListener { e ->
            Log.e("LocationCheck", "Failed to get location", e)
        }
}