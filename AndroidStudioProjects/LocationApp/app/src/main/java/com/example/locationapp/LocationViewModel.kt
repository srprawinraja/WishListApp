package com.example.locationapp

import android.view.View
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel

class LocationViewModel:ViewModel() {
    private val _location = mutableStateOf<LocationData?>(null)
    var location: State<LocationData?> =_location

    fun updateLocation(newLocationData: LocationData) {
        _location.value=newLocationData
    }
}