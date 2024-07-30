package com.example.locationapp

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.locationapp.ui.theme.LocationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel:LocationViewModel=viewModel()
            LocationAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    myApp(viewModel)

                }

            }
        }
    }
}
@Composable
fun myApp(viewModel: LocationViewModel){
    var context= LocalContext.current
    var locationUtils=LocationUtils(context)
    LocationDisplay(locationUtils,viewModel, context)
}
@Composable
fun LocationDisplay(
    locationUtils: LocationUtils,
    viewModel: LocationViewModel,
    context: Context) {
    val location = viewModel.location.value
    val address = location?.let {
        locationUtils.reverseGeocode(location)
    }
    val process= remember {
        mutableStateOf("")
    }

    var requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = {
            permissions->
            if(permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
                && permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true){
                    locationUtils.requestLocationUpdates(viewModel)

            }
            else{
                var rationalRequired = ActivityCompat.shouldShowRequestPermissionRationale(
                    context as ComponentActivity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) || ActivityCompat.shouldShowRequestPermissionRationale(
                    context as ComponentActivity,
                    Manifest.permission.ACCESS_COARSE_LOCATION)

                if(rationalRequired){
                    Toast.makeText(context, "Location permission required", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(context, "Location permission denied. Go to setting to enable", Toast.LENGTH_SHORT).show()
                }
            }
        })
        

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        process.value="Get Location"

        if(location != null){
            Text(text = "Latitude: ${location.latitude}")
            Text(text = "Longitude: ${location.longitude}")
            Text(text = "Address: $address")
        }
        else Text(text = "Location not available")
        Button(onClick =
        {
            process.value="retrieving location...."
            if(locationUtils.isLocationProvided()){
                locationUtils.requestLocationUpdates(viewModel)
            }
            else{
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        }) {
            Text(text = "${process.value}")

        }
    }
}

@Preview(showBackground = true)
@Composable
fun LocationDisplayPreview() {
    LocationAppTheme {
        myApp(viewModel())
    }
}