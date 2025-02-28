package com.example.newsappcomposeviewpager.news

import com.example.newsappcomposeviewpager.api.NewsApi
import com.example.newsappcomposeviewpager.model.NewsResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(private var newsApi: NewsApi) {
    suspend fun getTopHeadlines(apiKey:String,country:String,category:String):NewsResponse{
        return newsApi.getTopHeadlines(apiKey,country,category)
    }

    suspend fun getEverything(apiKey: String,q:String):NewsResponse{
        return newsApi.getEverything(apiKey,q)
    }
}