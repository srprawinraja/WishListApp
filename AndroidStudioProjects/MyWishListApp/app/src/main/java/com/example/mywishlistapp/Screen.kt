package com.example.mywishlistapp

sealed class Screen(val route:String) {
    object HomeScreen:Screen("Home_Screen")
    object AddScreen:Screen("add")
}