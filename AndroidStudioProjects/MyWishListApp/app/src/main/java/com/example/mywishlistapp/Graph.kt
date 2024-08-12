package com.example.mywishlistapp

import android.content.Context
import androidx.room.Room
import com.example.mywishlistapp.data.WishDatabase
import com.example.mywishlistapp.data.WishRepositary
// dependency injection
object Graph { // pbject makes it a singleton. Only one instance can made
    lateinit var database: WishDatabase

    val wishRepositary by lazy {  // lazy makes it a singleton
        WishRepositary(database.wishDao())
    }

    fun provide(context: Context){
        database= Room.databaseBuilder(context, WishDatabase::class.java, "wishlist.db").build()
    }

}