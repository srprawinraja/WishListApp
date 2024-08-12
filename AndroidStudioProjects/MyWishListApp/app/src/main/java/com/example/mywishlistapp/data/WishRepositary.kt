package com.example.mywishlistapp.data

import com.example.mywishlistapp.Wish
import  kotlinx.coroutines.flow.Flow

class WishRepositary(private val wishDao: WishDao) {

    suspend fun addWish(wish:Wish){
        wishDao.addWish(wish)
    }

    fun getWishes(): Flow<List<Wish>>  = wishDao.getAllWishes()

    fun getWishById(id:Long):  Flow<Wish>{
        return wishDao.getAllWishesById(id)
    }

    suspend fun updateWish(wish: Wish){
        wishDao.updateWish(wish)
    }
    suspend fun deleteWish(wish: Wish){
        wishDao.deleteWish(wish)
    }
}