package com.example.newsappcomposeviewpager.model

sealed class Screens (val screen:String) {
    data object Home:Screens("Home")
    data object Favorite:Screens("Favorite")
    data object Search:Screens("Search")
    data object Detail:Screens("Detail")
}