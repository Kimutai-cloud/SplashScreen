package com.example.splashscreen

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import com.google.android.gms.maps.model.LatLng
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*

object LocationUtils {

    @SuppressLint("MissingPermission")
    fun getLastLocation(
        context: Context,
        fusedLocationClient: FusedLocationProviderClient,
        locationCallback: (LatLng) -> Unit
    ) {
        if (checkPermissions(context)) {
            if (isLocationEnabled(context)) {
                fusedLocationClient.lastLocation.addOnCompleteListener { task ->
                    val location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData(fusedLocationClient, locationCallback)
                    } else {
                        val currentLocation = LatLng(location.latitude, location.longitude)
                        locationCallback.invoke(currentLocation)
                    }
                }
            }
        } else {
            // Handle the case where permissions are not granted
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestNewLocationData(
        fusedLocationClient: FusedLocationProviderClient,
        locationCallback: (LatLng) -> Unit
    ) {
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        fusedLocationClient.requestLocationUpdates(
            mLocationRequest,
            getLocationCallback(locationCallback),
            Looper.myLooper()
        )
    }

    private fun getLocationCallback(locationCallback: (LatLng) -> Unit): LocationCallback {
        return object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                val mLastLocation: Location? = locationResult.lastLocation
                if (mLastLocation != null) {
                    val currentLocation = LatLng(mLastLocation.latitude, mLastLocation.longitude)
                    locationCallback.invoke(currentLocation)
                }
            }
        }
    }

    private fun isLocationEnabled(context: Context): Boolean {
        val locationManager: LocationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun checkPermissions(context: Context): Boolean {
        return ActivityCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    context,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }
}
