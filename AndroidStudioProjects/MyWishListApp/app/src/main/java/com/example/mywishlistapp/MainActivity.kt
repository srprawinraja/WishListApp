package com.example.mywishlistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.mywishlistapp.ui.theme.MyWishListAppTheme
import com.example.mywishlistapp.ui.theme.Navigation
import com.example.mywishlistapp.ui.theme.WishViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyWishListAppTheme {
                Surface(
                    modifier=Modifier.fillMaxSize(),
                    color= MaterialTheme.colorScheme.background
                ){
                    Navigation()
                }

            }
        }
    }
}



