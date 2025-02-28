package com.example.newsappcomposeviewpager.favorite

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert
    fun addFavorite(favorite: Favorite)

    @Query("SELECT * FROM favoriteNewsComposeVP")
    fun getFavorite():Flow<List<Favorite>>

    @Query("SELECT * FROM favoriteNewsComposeVP WHERE title=:title")
    fun getFavoriteId(title:String):Flow<Favorite>

    @Query("DELETE FROM favoriteNewsComposeVP WHERE title=:title")
    fun deleteFavorite(title: String)
}
