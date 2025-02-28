package com.example.newsappcomposeviewpager.favorite

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Favorite::class], version = 2)
abstract class FavoriteDB:RoomDatabase() {
    abstract fun favoriteDao():FavoriteDao
}