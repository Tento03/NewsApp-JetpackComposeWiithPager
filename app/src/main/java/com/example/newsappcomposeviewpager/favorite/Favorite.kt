package com.example.newsappcomposeviewpager.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("favoriteNewsComposeVP")
data class Favorite(
    val author: String?,
    val content: String,
    val description: String,
    val publishedAt: String,
    @PrimaryKey
    val title: String,
    val url: String,
    val urlToImage: String
)