package com.example.mywishlistapp.data

import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.mywishlistapp.Wish

@Database(
    entities = [Wish::class],
    version = 1,
    exportSchema = true
)
abstract class WishDatabase:RoomDatabase() {
    abstract fun wishDao():WishDao
}
