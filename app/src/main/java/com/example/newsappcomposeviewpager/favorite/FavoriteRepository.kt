package com.example.newsappcomposeviewpager.favorite

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteRepository @Inject constructor(private var dao: FavoriteDao){
    suspend fun addFavorite(favorite: Favorite){
        return dao.addFavorite(favorite)
    }
    suspend fun getFavorite():Flow<List<Favorite>>{
        return dao.getFavorite()
    }
    suspend fun getFavoriteId(title:String):Flow<Favorite>{
        return dao.getFavoriteId(title)
    }
    suspend fun deleteFavorite(title: String){
        return dao.deleteFavorite(title)
    }
}