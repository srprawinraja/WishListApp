package com.example.locationapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Looper
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import java.util.Locale

class LocationUtils(context: Context) {
    private val context = context
    private val _fusedLocationClient:FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission", "SuspiciousIndentation")
    fun requestLocationUpdates(viewModel: LocationViewModel){
        val locationCallBack=object:LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                locationResult.lastLocation?.let {
                    val location =
                            LocationData(latitude = it.latitude, longitude = it.longitude)
                    viewModel.updateLocation(location)

                }
            }
        }
        val locationRequest= LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,1000  // updates location every 1000 seconds
        ).build()
          _fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallBack,
            Looper.getMainLooper()
        )
    }

    fun isLocationProvided():Boolean{
        return ActivityCompat.checkSelfPermission(context,android.Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED
                &&  ActivityCompat.checkSelfPermission(context,android.Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED
    }

    fun reverseGeocode(location:LocationData):String{
        var geoCoder=Geocoder(context, Locale.getDefault())
        val coordinate=LatLng(location.latitude,location.longitude )
        var addresses:MutableList<Address>? = geoCoder.getFromLocation(coordinate.latitude,coordinate.longitude,1)
        return if(addresses?.isNotEmpty()==true){
            addresses[0].getAddressLine(0)
        }else "Unknown Location"
    }
}