package com.example.newsappcomposeviewpager.module

import android.content.Context
import androidx.room.Room
import com.example.newsappcomposeviewpager.api.NewsApi
import com.example.newsappcomposeviewpager.favorite.Favorite
import com.example.newsappcomposeviewpager.favorite.FavoriteDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesRetrofit():Retrofit=Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun providesNewsApi(retrofit: Retrofit)=retrofit.create(NewsApi::class.java)

    @Singleton
    @Provides
    fun providesFavoriteDB(@ApplicationContext app:Context):FavoriteDB= Room.databaseBuilder(
        app.applicationContext,FavoriteDB::class.java,"FavoriteComposeVP"
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun providesFavoriteDao(db: FavoriteDB)=db.favoriteDao()
}