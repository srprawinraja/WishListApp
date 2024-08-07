package com.example.mywishlistapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mywishlistapp.AddEditDetailView
import com.example.mywishlistapp.HomeView
import com.example.mywishlistapp.Screen

@Composable
fun Navigation(viewModel: WishViewModel=viewModel(),
               navController:NavHostController= rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route)
    {
        composable(Screen.HomeScreen.route){
            HomeView(navController, viewModel)
        }
        composable(Screen.AddScreen.route){
            AddEditDetailView(id = 0L, viewModel =viewModel , navController = navController)
        }
    }
}
