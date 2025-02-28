package com.example.newsappcomposeviewpager.api

import com.example.newsappcomposeviewpager.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("apiKey")apiKey:String,
        @Query("country")country:String,
        @Query("category")category:String
    ):NewsResponse

    @GET("everything")
    suspend fun getEverything(
        @Query("apiKey")apiKey: String,
        @Query("q")q:String
    ):NewsResponse
}